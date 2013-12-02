package org.myftp.gattserver.csi.world.relations.moral;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.myftp.gattserver.csi.world.Knowledge;
import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractFamilyRelationType;
import org.myftp.gattserver.csi.world.relations.IRelationType;
import org.myftp.gattserver.csi.world.relations.moral.Husband;
import org.myftp.gattserver.csi.world.relations.moral.Wife;
import org.myftp.gattserver.csi.world.relations.tagging.Aunt;
import org.myftp.gattserver.csi.world.relations.tagging.Brother;
import org.myftp.gattserver.csi.world.relations.tagging.Cousin;
import org.myftp.gattserver.csi.world.relations.tagging.Father;
import org.myftp.gattserver.csi.world.relations.tagging.Mother;
import org.myftp.gattserver.csi.world.relations.tagging.Sister;
import org.myftp.gattserver.csi.world.relations.tagging.Uncle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractChild extends AbstractFamilyRelationType {

	private static final int MIN_AGE_DIFF_RANGE = 18;

	@Autowired
	private Wife wifeRelation;

	@Autowired
	private Husband husbandRelation;

	@Autowired
	private Son sonRelation;

	@Autowired
	private Daughter daughterRelation;

	public AbstractChild(String name) {
		super(name);
	}

	// vytvoøené vztahy se doèasnì uloží sem - pak se pøesypou do knowledge
	// všech zúèastnìných
	private Knowledge awareKnowledge = new Knowledge();
	// zúèastnìní lidé, kterým je potøeba dát vìdìt o novì vzniklých vztazích
	private Set<Person> awarePersons = new HashSet<>();

	private boolean relationDepthLimitationCheck(Person holdingPerson) {
		// OMEZENÍ HLOUBKY VZTAHÙ (jinak by mi z toho asi jeblo)
		// pokud osoba který má být synem/dcerou už má vlastní rodinu,
		// tedy má nìjakého syna nebo dceru, nepropojuj jí dál nahoru
		// výsledkem by bylo totiž procházení dìdù, vnuèek apod., což
		// vede prakticky ke "vztahové explozi", která sice je
		// implementovatelná, ale aktuálnì by se na tom jen zabíjel èas,
		// což v rámci psaní prototypu není žádoucí
		// TODO odstranit a rozšíøit o další aware vztahy a lidi, až
		// tento program nebude prototyp ...
		Set<Person> myOwnChildren;
		if (holdingPerson.isMale()) {
			myOwnChildren = worldKnowledge.getRelated(holdingPerson, Father.getInstance());
		} else {
			myOwnChildren = worldKnowledge.getRelated(holdingPerson, Mother.getInstance());
		}
		if (myOwnChildren != null && myOwnChildren.isEmpty() == false)
			return false;

		return true;
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {

		if (holdingPerson.isMale()) {
			if (this instanceof Daughter)
				return false;
		} else {
			if (this instanceof Son)
				return false;
		}

		if (relationDepthLimitationCheck(holdingPerson) == false)
			return false;

		double hPAge = holdingPerson.getAge(world.getYearOffset());
		double tPAge = targetPerson.getAge(world.getYearOffset());

		// vìkové rozdíly
		if (tPAge > hPAge + MIN_AGE_DIFF_RANGE)
			return false;

		if (worldKnowledge.checkBannedRelations(holdingPerson, targetPerson, Sister.getInstance(),
				Brother.getInstance(), Cousin.getInstance(), Aunt.getInstance(), Uncle.getInstance()) == false)
			return false;

		// ok, kontroly prošly - pøidej první,
		// kteøí budou obeznámeni s novými vztahy
		awarePersons.add(holdingPerson);
		awarePersons.add(targetPerson);

		// sourozenci
		Set<Person> siblings = new HashSet<>();
		Set<Person> sons = worldKnowledge.getRelated(targetPerson, sonRelation);
		Set<Person> daughters = worldKnowledge.getRelated(targetPerson, daughterRelation);
		if (sons != null)
			siblings.addAll(sons);
		if (daughters != null)
			siblings.addAll(daughters);

		// pøidej se jako potomek
		awareKnowledge.registerRelation(this, holdingPerson, targetPerson);

		// matka/otec
		awareKnowledge.registerRelation(targetPerson.isMale() ? Father.getInstance() : Mother.getInstance(),
				targetPerson, holdingPerson);
		Set<Person> related = worldKnowledge.getRelated(targetPerson, targetPerson.isMale() ? wifeRelation
				: husbandRelation);
		if (related != null) {
			for (Person partner : related) {

				// ani partner nesmí porušit limit hloubky rodinných vztahù :P
				if (relationDepthLimitationCheck(partner) == false)
					return false;

				// najdi sourozence získané od partnera mého biologického rodièe
				sons = worldKnowledge.getRelated(partner, sonRelation);
				daughters = worldKnowledge.getRelated(partner, daughterRelation);
				if (sons != null)
					siblings.addAll(sons);
				if (daughters != null)
					siblings.addAll(daughters);

				// pøidej se jako potomek partnera mého biologického rodièe
				awareKnowledge.registerRelation(this, holdingPerson, partner);
				awareKnowledge.registerRelation(targetPerson.isMale() ? Mother.getInstance() : Father.getInstance(),
						partner, holdingPerson);
			}
			awarePersons.addAll(related);
		}

		// sourozenec
		awarePersons.addAll(siblings);
		for (Person sibling : siblings) {
			// já jemu
			awareKnowledge.registerRelation(holdingPerson.isMale() ? Brother.getInstance() : Sister.getInstance(),
					holdingPerson, sibling);
			// on/ona mì
			awareKnowledge.registerRelation(sibling.isMale() ? Brother.getInstance() : Sister.getInstance(), sibling,
					holdingPerson);
		}

		writeKnowledgeToInvolved();

		return true;
	}

	private void writeKnowledgeToInvolved() {
		Map<IRelationType, Map<Person, Set<Person>>> relations = awareKnowledge.getRelationsByRelation();
		for (IRelationType relationType : relations.keySet()) {
			Map<Person, Set<Person>> psp = relations.get(relationType);
			for (Person holdingPerson : psp.keySet()) {
				Set<Person> targetPersons = psp.get(holdingPerson);
				for (Person targetPerson : targetPersons) {

					for (Person awarePerson : awarePersons) {
						awarePerson.getKnowledge().registerRelation(relationType, holdingPerson, targetPerson);
					}
					worldKnowledge.registerRelation(relationType, holdingPerson, targetPerson);
				}
			}
		}
	}
}

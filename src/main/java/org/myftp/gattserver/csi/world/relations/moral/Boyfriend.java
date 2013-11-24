package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractMoralRelationType;
import org.myftp.gattserver.csi.world.relations.IRelationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Boyfriend extends AbstractMoralRelationType {

	@Autowired
	private Mother mother;
	@Autowired
	private Sister sister;

	public Boyfriend() {
		super("Boyfriend");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {

		// P��tel mus� b�t mu�
		if (holdingPerson.isMale() == false)
			return false;

		// Gayov� odpust� - c�lov� osoba p��tele mus� b�t �ena
		if (targetPerson.isMale())
			return false;

		// Nem��e chodit se svoj� sestrou, matkou (ani jej� matkou nebo sestrou
		// apod.) a nesm� b�t �enat�
		IRelationType[] bannedFirstLevelRelations = new IRelationType[] {
				sister, mother };
		IRelationType[] bannedDeepLevelsRelations = bannedFirstLevelRelations;
		if (checkRecursivelyBannedRelations(holdingPerson, targetPerson,
				bannedFirstLevelRelations, bannedDeepLevelsRelations, true) == false)
			return false;

		holdingPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);
		targetPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);
		return true;
	}
}

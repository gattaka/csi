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

		// Pøítel musí být muž
		if (holdingPerson.isMale() == false)
			return false;

		// Gayové odpustí - cílová osoba pøítele musí být žena
		if (targetPerson.isMale())
			return false;

		// Nemùže chodit se svojí sestrou, matkou (ani její matkou nebo sestrou
		// apod.) a nesmí být ženatý
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

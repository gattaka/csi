package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.World;
import org.myftp.gattserver.csi.world.relations.AbstractMoralRelationType;
import org.myftp.gattserver.csi.world.relations.IRelationType;
import org.myftp.gattserver.csi.world.relations.tagging.Mother;
import org.myftp.gattserver.csi.world.relations.tagging.Sister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Boyfriend extends AbstractMoralRelationType {

	@Autowired
	private Mother mother;
	@Autowired
	private Sister sister;

	private static final int MAX_AGE_DIFF_RANGE = 5;
	private static final int MIN_AGE = 15;

	public Boyfriend() {
		super("Boyfriend");
	}

	@Override
	public double getPropability() {
		return 0.3;
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {

		// Pøítel musí být muž
		if (holdingPerson.isMale() == false)
			return false;

		// Gayové odpustí - cílová osoba pøítele musí být žena
		if (targetPerson.isMale())
			return false;

		double hPAge = holdingPerson.getAge(world.getYearOffset());
		double tPAge = targetPerson.getAge(world.getYearOffset());
		
		// minimální vìk k párování
		if (hPAge < MIN_AGE)
			return false;
		if (tPAge < MIN_AGE)
			return false;
		
		// vìkové rozdíly
		if (Math.abs(hPAge - tPAge) > MAX_AGE_DIFF_RANGE)
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

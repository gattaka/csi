package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractMoralRelationType;
import org.springframework.stereotype.Component;

@Component
public class Classmate extends AbstractMoralRelationType {

	private static final int MAX_AGE_DIFF_RANGE = 5;
	private static final int MIN_AGE = 7;
	private static final int MAX_AGE = 25;

	public Classmate() {
		super("Classmate");
	}

	@Override
	public double getPropability(Person holdingPerson, Person targetPerson) {
		return 0.3;
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {

		double hPAge = holdingPerson.getAge(world.getYearOffset());
		double tPAge = targetPerson.getAge(world.getYearOffset());

		// minimální vìk 
		if (hPAge < MIN_AGE)
			return false;
		if (tPAge < MIN_AGE)
			return false;

		// maximální vìk
		if (hPAge > MAX_AGE)
			return false;
		if (tPAge > MAX_AGE)
			return false;

		// vìkové rozdíly
		if (Math.abs(hPAge - tPAge) > MAX_AGE_DIFF_RANGE)
			return false;

		holdingPerson.getKnowledge().registerRelation(this, holdingPerson, targetPerson);
		targetPerson.getKnowledge().registerRelation(this, holdingPerson, targetPerson);

		return true;
	}

}

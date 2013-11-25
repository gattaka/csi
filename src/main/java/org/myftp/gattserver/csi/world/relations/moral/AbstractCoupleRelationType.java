package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractMoralRelationType;

public abstract class AbstractCoupleRelationType extends
		AbstractMoralRelationType {

	private static final int MAX_AGE_DIFF_RANGE = 5;
	private static final int MIN_AGE = 15;

	public AbstractCoupleRelationType(String name) {
		super(name);
	}

	@Override
	public double getPropability(Person holdingPerson, Person targetPerson) {
		return 0.3;
	}

	public boolean apply(Person him, Person her) {

		// Pøítel musí být muž
		if (him.isMale() == false)
			return false;

		// Gayové odpustí - cílová osoba pøítele musí být žena
		if (her.isMale())
			return false;

		double hPAge = him.getAge(world.getYearOffset());
		double tPAge = her.getAge(world.getYearOffset());

		// minimální vìk k párování
		if (hPAge < MIN_AGE)
			return false;
		if (tPAge < MIN_AGE)
			return false;

		// vìkové rozdíly
		if (Math.abs(hPAge - tPAge) > MAX_AGE_DIFF_RANGE)
			return false;

		if (specificApply(him, her) == false)
			return false;

		him.getKnowledge().registerRelation(this, him, her);
		her.getKnowledge().registerRelation(this, him, her);
		return true;
	}

	protected abstract boolean specificApply(Person him, Person her);
}

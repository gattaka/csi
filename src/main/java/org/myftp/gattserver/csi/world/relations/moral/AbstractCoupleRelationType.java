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

		// P��tel mus� b�t mu�
		if (him.isMale() == false)
			return false;

		// Gayov� odpust� - c�lov� osoba p��tele mus� b�t �ena
		if (her.isMale())
			return false;

		double hPAge = him.getAge(world.getYearOffset());
		double tPAge = her.getAge(world.getYearOffset());

		// minim�ln� v�k k p�rov�n�
		if (hPAge < MIN_AGE)
			return false;
		if (tPAge < MIN_AGE)
			return false;

		// v�kov� rozd�ly
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

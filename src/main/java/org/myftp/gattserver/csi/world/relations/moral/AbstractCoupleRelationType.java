package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractMoralRelationType;

public abstract class AbstractCoupleRelationType extends AbstractMoralRelationType {

	private static final int MAX_AGE_DIFF_RANGE = 5;
	private static final int MIN_AGE = 15;

	public AbstractCoupleRelationType(String name) {
		super(name);
	}

	@Override
	public double getPropability(Person holdingPerson, Person targetPerson) {
		return 0.3;
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {

		// Pohlav� se mus� li�it (homosexu�ln� vztahy zat�m nevedeme, ne�e�it
		// dojde na n� ... na ka�d�ho jednou dojde)
		if (holdingPerson.isMale() == targetPerson.isMale())
			return false;

		double hPAge = holdingPerson.getAge(world.getYearOffset());
		double tPAge = targetPerson.getAge(world.getYearOffset());

		// minim�ln� v�k k p�rov�n�
		if (hPAge < MIN_AGE)
			return false;
		if (tPAge < MIN_AGE)
			return false;

		// v�kov� rozd�ly
		if (Math.abs(hPAge - tPAge) > MAX_AGE_DIFF_RANGE)
			return false;

		if (specificApply(holdingPerson, targetPerson) == false)
			return false;

		holdingPerson.getKnowledge().registerRelation(this, holdingPerson, targetPerson);
		targetPerson.getKnowledge().registerRelation(this, holdingPerson, targetPerson);
		return true;
	}

	protected abstract boolean specificApply(Person holdingPerson, Person targetPerson);
}

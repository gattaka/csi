package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Immorality;
import org.myftp.gattserver.csi.world.Person;

public abstract class AbstractVeryImmoralRelationType extends
		AbstractRelationType {

	public AbstractVeryImmoralRelationType(String name) {
		super(name, Immorality.VERY_IMMORAL);
	}

	public double getPropability(Person holdingPerson, Person targetPerson) {
		return 0.01;
	}
}

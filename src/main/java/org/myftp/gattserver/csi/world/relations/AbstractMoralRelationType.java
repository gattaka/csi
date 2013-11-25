package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Immorality;
import org.myftp.gattserver.csi.world.Person;

public abstract class AbstractMoralRelationType extends AbstractRelationType {

	public AbstractMoralRelationType(String name) {
		super(name, Immorality.MORAL);
	}

	public double getPropability(Person holdingPerson, Person targetPerson) {
		return 0.8;
	}
	
}

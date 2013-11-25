package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Immorality;
import org.myftp.gattserver.csi.world.Person;

public abstract class AbstractImmoralRelationType extends AbstractRelationType {

	public AbstractImmoralRelationType(String name) {
		super(name, Immorality.IMMORAL);
	}
	
	public double getPropability(Person holdingPerson, Person targetPerson) {
		return 0.3;
	}

}

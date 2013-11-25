package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Immorality;
import org.myftp.gattserver.csi.world.Person;

public abstract class AbstractSlightlyImmoralRelationType extends AbstractRelationType {

	public AbstractSlightlyImmoralRelationType(String name) {
		super(name, Immorality.SLIGHTLY_IMMORAL);
	}

	public double getPropability(Person holdingPerson, Person targetPerson) {
		return 0.4;
	}
}

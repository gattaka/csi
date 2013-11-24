package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Immorality;

public abstract class AbstractMoralRelationType extends AbstractRelationType {

	public AbstractMoralRelationType(String name) {
		super(name, Immorality.MORAL);
	}

	public double getPropability() {
		return 0.8;
	}
	
}

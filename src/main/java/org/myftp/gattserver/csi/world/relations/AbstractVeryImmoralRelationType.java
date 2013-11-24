package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Immorality;

public abstract class AbstractVeryImmoralRelationType extends
		AbstractRelationType {

	public AbstractVeryImmoralRelationType(String name) {
		super(name, Immorality.VERY_IMMORAL);
	}

	public double getPropability() {
		return 0.01;
	}
}

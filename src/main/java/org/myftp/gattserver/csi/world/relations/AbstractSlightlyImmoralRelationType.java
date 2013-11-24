package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Immorality;

public abstract class AbstractSlightlyImmoralRelationType extends AbstractRelationType {

	public AbstractSlightlyImmoralRelationType(String name) {
		super(name, Immorality.SLIGHTLY_IMMORAL);
	}

	public double getPropability() {
		return 0.4;
	}
}

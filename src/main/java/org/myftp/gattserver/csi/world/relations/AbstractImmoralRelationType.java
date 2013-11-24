package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Immorality;

public abstract class AbstractImmoralRelationType extends AbstractRelationType {

	public AbstractImmoralRelationType(String name) {
		super(name, Immorality.IMMORAL);
	}
	
	public double getPropability() {
		return 0.3;
	}

}

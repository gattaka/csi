package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Immorality;

public abstract class AbstractRelationType implements IRelationType {

	private String name;
	private Immorality immorality = Immorality.MORAL;

	public AbstractRelationType(String name, Immorality immorality) {
		this.name = name;
		this.immorality = immorality;
	}

	public String getName() {
		return name;
	}

	public Immorality getImmorality() {
		return immorality;
	}

}

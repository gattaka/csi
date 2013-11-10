package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Immorality;

public abstract class AbstractSpecialRelationType extends AbstractRelationType {

	public AbstractSpecialRelationType(String name) {
		super(name, Immorality.SPECIAL);
	}

}

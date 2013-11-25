package org.myftp.gattserver.csi.world.relations.tagging;

import org.myftp.gattserver.csi.world.relations.AbstractTaggingRelationType;

public class Brother extends AbstractTaggingRelationType {

	private static Brother INSTANCE = null;

	public static Brother getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Brother();
		}
		return INSTANCE;
	}

	private Brother() {
		super("Brother");
	}

}

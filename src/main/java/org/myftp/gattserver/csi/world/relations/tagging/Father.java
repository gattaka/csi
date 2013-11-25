package org.myftp.gattserver.csi.world.relations.tagging;

import org.myftp.gattserver.csi.world.relations.AbstractTaggingRelationType;

public class Father extends AbstractTaggingRelationType {

	private static Father INSTANCE = null;

	public static Father getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Father();
		}
		return INSTANCE;
	}

	private Father() {
		super("Father");
	}

}

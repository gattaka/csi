package org.myftp.gattserver.csi.world.relations.tagging;

import org.myftp.gattserver.csi.world.relations.AbstractTaggingRelationType;

public class Cousin extends AbstractTaggingRelationType {

	private static Cousin INSTANCE = null;

	public static Cousin getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Cousin();
		}
		return INSTANCE;
	}

	private Cousin() {
		super("Cousin");
	}

}

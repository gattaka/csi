package org.myftp.gattserver.csi.world.relations.tagging;

import org.myftp.gattserver.csi.world.relations.AbstractTaggingRelationType;

public class Mother extends AbstractTaggingRelationType {

	private static Mother INSTANCE = null;

	public static Mother getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Mother();
		}
		return INSTANCE;
	}

	private Mother() {
		super("Mother");
	}

}

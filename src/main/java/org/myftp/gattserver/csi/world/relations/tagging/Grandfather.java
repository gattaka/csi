package org.myftp.gattserver.csi.world.relations.tagging;

import org.myftp.gattserver.csi.world.relations.AbstractTaggingRelationType;

public class Grandfather extends AbstractTaggingRelationType {

	private static Grandfather INSTANCE = null;

	public static Grandfather getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Grandfather();
		}
		return INSTANCE;
	}

	private Grandfather() {
		super("Grandfather");
	}

}

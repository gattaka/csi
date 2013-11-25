package org.myftp.gattserver.csi.world.relations.tagging;

import org.myftp.gattserver.csi.world.relations.AbstractTaggingRelationType;

public class Grandmother extends AbstractTaggingRelationType {

	private static Grandmother INSTANCE = null;

	public static Grandmother getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Grandmother();
		}
		return INSTANCE;
	}

	private Grandmother() {
		super("Grandmother");
	}

}

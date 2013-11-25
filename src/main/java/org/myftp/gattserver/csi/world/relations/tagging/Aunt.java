package org.myftp.gattserver.csi.world.relations.tagging;

import org.myftp.gattserver.csi.world.relations.AbstractTaggingRelationType;

public class Aunt extends AbstractTaggingRelationType {

	private static Aunt INSTANCE = null;

	public static Aunt getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Aunt();
		}
		return INSTANCE;
	}

	private Aunt() {
		super("Aunt");
	}

}

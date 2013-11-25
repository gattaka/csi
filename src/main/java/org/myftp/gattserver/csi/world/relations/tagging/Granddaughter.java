package org.myftp.gattserver.csi.world.relations.tagging;

import org.myftp.gattserver.csi.world.relations.AbstractTaggingRelationType;

public class Granddaughter extends AbstractTaggingRelationType {

	private static Granddaughter INSTANCE = null;

	public static Granddaughter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Granddaughter();
		}
		return INSTANCE;
	}

	private Granddaughter() {
		super("Granddaughter");
	}

}

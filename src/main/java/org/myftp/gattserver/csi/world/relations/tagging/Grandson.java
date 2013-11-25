package org.myftp.gattserver.csi.world.relations.tagging;

import org.myftp.gattserver.csi.world.relations.AbstractTaggingRelationType;

public class Grandson extends AbstractTaggingRelationType {

	private static Grandson INSTANCE = null;

	public static Grandson getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Grandson();
		}
		return INSTANCE;
	}

	private Grandson() {
		super("Grandson");
	}

}

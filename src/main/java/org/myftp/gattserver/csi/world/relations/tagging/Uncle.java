package org.myftp.gattserver.csi.world.relations.tagging;

import org.myftp.gattserver.csi.world.relations.AbstractTaggingRelationType;

public class Uncle extends AbstractTaggingRelationType {

	private static Uncle INSTANCE = null;

	public static Uncle getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Uncle();
		}
		return INSTANCE;
	}

	private Uncle() {
		super("Uncle");
	}

}

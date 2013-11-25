package org.myftp.gattserver.csi.world.relations.tagging;

import org.myftp.gattserver.csi.world.relations.AbstractTaggingRelationType;

public class Sister extends AbstractTaggingRelationType {

	private static Sister INSTANCE = null;

	public static Sister getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Sister();
		}
		return INSTANCE;
	}
	
	private Sister() {
		super("Sister");
	}

}

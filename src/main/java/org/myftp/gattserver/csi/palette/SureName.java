package org.myftp.gattserver.csi.palette;

import java.util.Random;

public enum SureName {

	SMITH, JONES, WILLIAMS, TAYLOR, BROWN, DAVIES, EVANS, WILSON, THOMAS, JOHNSON;

	public static SureName generateRandom() {
		Random random = new Random();
		return SureName.values()[random.nextInt(SureName.values().length)];
	}

}

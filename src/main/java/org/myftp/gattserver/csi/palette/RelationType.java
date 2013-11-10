package org.myftp.gattserver.csi.palette;

import org.myftp.gattserver.csi.world.Immorality;
import static org.myftp.gattserver.csi.world.Immorality.*;

@Deprecated
public enum RelationType {

	/**
	 * Lehká nemorálnost
	 */
	MARRIAGE_FROM_REASON("Marriage from reason", SLIGHTLY_IMMORAL),
	HUMILIATION("Humiliation", SLIGHTLY_IMMORAL),
	STRIFE("Strife", SLIGHTLY_IMMORAL),
	RIVAL("Rival", SLIGHTLY_IMMORAL),
	
	/**
	 * Nemorálnost
	 */
	AFFAIR("Cheating on", IMMORAL),
	WOMANIZER("Womanizer", IMMORAL),
	DEBTS("Debts", IMMORAL),
	THEFT("Theft", IMMORAL),
	ALCOHOLIC("Alcoholic", IMMORAL),
	
	/**
	 * Tìžká nemorálnost
	 */
	BLACKMAIL("Blackmail", VERY_IMMORAL),
	ABUSE("Abuse", VERY_IMMORAL),
	BRUISER("Bruiser", VERY_IMMORAL),
	RAPE("Rape", VERY_IMMORAL),

	/**
	 * Speciální
	 */
	MURDER("Murder", SPECIAL),
	VICTIM("Victim", SPECIAL),
	FINDER("Finder", SPECIAL),
	;
	
	
	private String name;
	private Immorality immorality;
	
	private RelationType(String name, Immorality immorality) {
		this.name = name;
		this.immorality = immorality;
	}
	
	public String getName() {
		return name;
	}
	public Immorality getImmorality() {
		return immorality;
	}
	
	
	
}

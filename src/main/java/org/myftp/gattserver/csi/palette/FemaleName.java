package org.myftp.gattserver.csi.palette;

import java.util.Random;

/**
 * Provizorní DB ženských jmen
 * http://www.20000-names.com/female_english_names_17.htm
 * 
 * @author Hynek
 * 
 */
public enum FemaleName implements FirstName {

	ADELE, ALEX, ALICE, REGINA, MARY, RONA, ROSE, RYANNE, SAL, SARA;

	public static FemaleName generateRandom() {
		Random random = new Random();
		return FemaleName.values()[random.nextInt(FemaleName.values().length)];
	}

}

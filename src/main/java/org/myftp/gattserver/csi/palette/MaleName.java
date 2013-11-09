package org.myftp.gattserver.csi.palette;

import java.util.Random;

/**
 * Provizorní DB mužských jmen http://www.behindthename.com/names/usage/english
 * 
 * @author Hynek
 * 
 */
public enum MaleName implements FirstName {

	ADAM, JIM, JACK, PETER, MARK, PAUL, DAVID, JOHN, WILLIAM, ROBERT, RICHARD, HENRY;

	public static FirstName generateRandom() {
		Random random = new Random();
		return MaleName.values()[random.nextInt(MaleName.values().length)];
	}

}

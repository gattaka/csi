package org.myftp.gattserver.csi.palette;

import java.util.Random;

public class Address {

	private static String[] addresses = new String[] { "Baker street",
			"Causton alley", "Europe street", "Highlane", "Breeks",
			"Jackobstown", "Redtown" };

	public static Address generateRandomAddress() {
		Random random = new Random();
		return new Address(addresses[random.nextInt(addresses.length)]);
	}

	private String address;

	private Address(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return address;
	}

}

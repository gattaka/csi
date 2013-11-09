package org.myftp.gattserver.csi.world;

public class Relation {

	private String type;

	private Person holdingPerson;
	private Person targetPerson;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Person getHoldingPerson() {
		return holdingPerson;
	}

	public void setHoldingPerson(Person holdingPerson) {
		this.holdingPerson = holdingPerson;
	}

	public Person getTargetPerson() {
		return targetPerson;
	}

	public void setTargetPerson(Person targetPerson) {
		this.targetPerson = targetPerson;
	}

}

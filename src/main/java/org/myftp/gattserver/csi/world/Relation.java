package org.myftp.gattserver.csi.world;

import org.myftp.gattserver.csi.world.relations.IRelationType;

public class Relation {

	private IRelationType type;

	private Person holdingPerson;
	private Person targetPerson;

	public Relation(Person holdingPerson, Person targetPerson,
			IRelationType type) {
		this.holdingPerson = holdingPerson;
		this.targetPerson = targetPerson;
		this.type = type;
	}

	public IRelationType getType() {
		return type;
	}

	public Person getHoldingPerson() {
		return holdingPerson;
	}

	public Person getTargetPerson() {
		return targetPerson;
	}

}

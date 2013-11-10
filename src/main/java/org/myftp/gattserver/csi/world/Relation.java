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

	@Override
	public int hashCode() {
		int result = 19;
		result = 17 * result + type.hashCode();
		result = 17 * result + holdingPerson.getFingerprint();
		result = 17 * result + targetPerson.getFingerprint();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Relation) {
			Relation rel = (Relation) obj;
			if (rel.getType().equals(type) == false)
				return false;
			if (rel.getHoldingPerson().getFingerprint() != holdingPerson
					.getFingerprint())
				return false;
			if (rel.getTargetPerson().getFingerprint() != targetPerson
					.getFingerprint())
				return false;
			return true;
		}
		return false;
	}

}

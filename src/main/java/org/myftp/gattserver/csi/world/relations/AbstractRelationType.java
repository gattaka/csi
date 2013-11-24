package org.myftp.gattserver.csi.world.relations;

import java.util.Map;

import org.myftp.gattserver.csi.world.Immorality;
import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.World;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRelationType implements IRelationType {

	@Autowired
	private World world;

	private String name;
	private Immorality immorality = Immorality.MORAL;

	public AbstractRelationType(String name, Immorality immorality) {
		this.name = name;
		this.immorality = immorality;
	}

	protected abstract boolean apply(Person holdingPerson, Person targetPerson);

	public boolean applyRelation(Person holdingPerson, Person targetPerson) {

		// test existence daného vztahu
		Map<Person, Person> relations = world.getKnowledge()
				.getRelationsByRelation().get(this);
		if (relations != null
				&& relations.get(targetPerson).equals(holdingPerson))
			return false;

		return apply(holdingPerson, targetPerson);
	}

	public String getName() {
		return name;
	}

	public Immorality getImmorality() {
		return immorality;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IRelationType) {
			IRelationType type = (IRelationType) obj;
			return type.getName().equals(name);
		}
		return false;
	}

}

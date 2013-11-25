package org.myftp.gattserver.csi.world.relations;

import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.myftp.gattserver.csi.world.Immorality;
import org.myftp.gattserver.csi.world.Knowledge;
import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.World;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRelationType implements IRelationType {

	@Autowired
	protected World world;
	protected Knowledge worldKnowledge;

	private String name;
	private Immorality immorality = Immorality.MORAL;

	@PostConstruct
	private void init() {
		worldKnowledge = world.getKnowledge();
	}

	public AbstractRelationType(String name, Immorality immorality) {
		this.name = name;
		this.immorality = immorality;
	}

	protected abstract boolean apply(Person holdingPerson, Person targetPerson);

	public boolean applyRelation(Person holdingPerson, Person targetPerson) {

		// test existence daného vztahu
		Map<Person, Set<Person>> relations = world.getKnowledge()
				.getRelationsByRelation().get(this);
		if (relations != null
				&& relations.get(targetPerson).equals(holdingPerson))
			return false;

		boolean result = apply(holdingPerson, targetPerson);
		if (result)
			worldKnowledge.registerRelation(this, holdingPerson, targetPerson);
		return result;
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

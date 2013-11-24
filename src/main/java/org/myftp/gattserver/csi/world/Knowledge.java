package org.myftp.gattserver.csi.world;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.myftp.gattserver.csi.world.relations.IRelationType;

public class Knowledge {

	private Map<IRelationType, Map<Person, Person>> relationsByRelation = new HashMap<IRelationType, Map<Person, Person>>();
	private Map<Person, Map<IRelationType, Person>> relationsByPerson = new HashMap<Person, Map<IRelationType, Person>>();

	// znalost lidí
	private Set<Person> persons = new HashSet<Person>();

	public Map<IRelationType, Map<Person, Person>> getRelationsByRelation() {
		return relationsByRelation;
	}

	public void setRelationsByRelation(
			Map<IRelationType, Map<Person, Person>> relationsByRelation) {
		this.relationsByRelation = relationsByRelation;
	}

	public Map<Person, Map<IRelationType, Person>> getRelationsByPerson() {
		return relationsByPerson;
	}

	public void setRelationsByPerson(
			Map<Person, Map<IRelationType, Person>> relationsByPerson) {
		this.relationsByPerson = relationsByPerson;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

}

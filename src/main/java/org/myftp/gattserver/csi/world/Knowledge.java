package org.myftp.gattserver.csi.world;

import java.util.HashSet;
import java.util.Set;

public class Knowledge {

	private Set<Relation> relations = new HashSet<Relation>();
	private Set<Person> persons = new HashSet<Person>();

	public Set<Relation> getRelations() {
		return relations;
	}

	public void setRelations(Set<Relation> relations) {
		this.relations = relations;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

}
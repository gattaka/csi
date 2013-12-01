package org.myftp.gattserver.csi.world;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.myftp.gattserver.csi.world.relations.IRelationType;

public class Knowledge {

	// znalost vztahù a lidí - daná osoba mùže mít tento vztah s více osobami
	private Map<IRelationType, Map<Person, Set<Person>>> relationsByRelation = new HashMap<IRelationType, Map<Person, Set<Person>>>();
	private Map<Person, Map<IRelationType, Set<Person>>> relationsByHoldingPerson = new HashMap<Person, Map<IRelationType, Set<Person>>>();

	// znalost lidí
	private Set<Person> persons = new HashSet<Person>();

	public Set<Person> getPersons() {
		return persons;
	}

	public Map<IRelationType, Map<Person, Set<Person>>> getRelationsByRelation() {
		return relationsByRelation;
	}

	public Set<Person> getTargetPersonsByHoldingPersonAndRelation(
			Person holdingPerson, IRelationType relation) {
		Map<IRelationType, Set<Person>> relations = relationsByHoldingPerson
				.get(holdingPerson);
		if (relations == null)
			return null; // tato osoba nemá s nikým žádné vztahy
		return relations.get(relation);
	}

	public boolean isInRelation(Person holdingPerson, IRelationType relation,
			Person targetPerson) {
		Map<IRelationType, Set<Person>> relations = relationsByHoldingPerson
				.get(holdingPerson);
		if (relations == null)
			return false;
		Set<Person> persons = relations.get(relation);
		if (persons == null)
			return false;
		return persons.contains(targetPerson);
	}

	public boolean isInRelation(Person holdingPerson, IRelationType relation) {
		Map<IRelationType, Set<Person>> relations = relationsByHoldingPerson
				.get(holdingPerson);
		if (relations == null)
			return false;
		Set<Person> persons = relations.get(relation);
		return persons != null;
	}

	public boolean checkBannedRelations(Person holdingPerson,
			Person targetPerson, IRelationType... bannedRelations) {

		for (IRelationType bannedRelation : bannedRelations) {
			Set<Person> persons = getTargetPersonsByHoldingPersonAndRelation(
					holdingPerson, bannedRelation);
			if (persons != null) {
				if (persons.contains(targetPerson))
					return false;
			}
		}
		return true;
	}

	public boolean checkRecursivelyBannedRelations(Person holdingPerson,
			Person targetPerson, IRelationType firstLevelRelations[],
			IRelationType deepLevelsRelations[], boolean firstLevel) {

		IRelationType bannedRelations[] = firstLevel ? firstLevelRelations
				: deepLevelsRelations;

		for (IRelationType bannedRelation : bannedRelations) {
			Set<Person> persons = getTargetPersonsByHoldingPersonAndRelation(
					holdingPerson, bannedRelation);
			if (persons != null) {
				if (persons.contains(targetPerson))
					return false;
				for (Person p : persons) {
					if (checkRecursivelyBannedRelations(p, targetPerson,
							firstLevelRelations, deepLevelsRelations, false) == false)
						return false;
				}
			}
		}

		return true;
	}

	public void registerRelation(IRelationType relationType,
			Person holdingPerson, Person targetPerson) {
		registerRelationByRelation(relationType, holdingPerson, targetPerson);
		registerRelationByHoldingPerson(relationType, holdingPerson,
				targetPerson);
	}

	// TODO test
	public void removeRelation(IRelationType relationType,
			Person holdingPerson, Person targetPerson) {
		removeRelationByRelation(relationType, holdingPerson, targetPerson);
		removeRelationByHoldingPerson(relationType, holdingPerson, targetPerson);
	}

	/*
	 * PRIVATE metody
	 */

	private void removeRelationError() {
		throw new IllegalStateException("Trying to remove nonexisting relation");
	}

	// TODO test
	private void removeRelationByRelation(IRelationType relationType,
			Person holdingPerson, Person targetPerson) {
		Map<Person, Set<Person>> relations = relationsByRelation
				.get(relationType);
		if (relations == null)
			removeRelationError();
		Set<Person> targetPersons = relations.get(holdingPerson);
		if (targetPersons == null)
			removeRelationError();
		if (targetPersons.remove(targetPerson) == false)
			removeRelationError();
		if (targetPersons.isEmpty())
			relations.remove(holdingPerson);
	}

	// TODO test
	private void removeRelationByHoldingPerson(IRelationType relationType,
			Person holdingPerson, Person targetPerson) {
		Map<IRelationType, Set<Person>> relations = relationsByHoldingPerson
				.get(holdingPerson);
		if (relations == null)
			removeRelationError();
		Set<Person> targetPersons = relations.get(relationType);
		if (targetPersons == null)
			removeRelationError();
		if (targetPersons.remove(targetPerson) == false)
			removeRelationError();
		if (targetPersons.isEmpty())
			relations.remove(relationType);
	}

	private void registerRelationByRelation(IRelationType relationType,
			Person holdingPerson, Person targetPerson) {
		Map<Person, Set<Person>> relations = relationsByRelation
				.get(relationType);
		if (relations == null) {
			relations = new HashMap<Person, Set<Person>>();
			relationsByRelation.put(relationType, relations);
		}
		Set<Person> targetPersons = relations.get(holdingPerson);
		if (targetPersons == null) {
			targetPersons = new HashSet<Person>();
			relations.put(holdingPerson, targetPersons);
		}
		targetPersons.add(targetPerson);
	}

	private void registerRelationByHoldingPerson(IRelationType relationType,
			Person holdingPerson, Person targetPerson) {
		Map<IRelationType, Set<Person>> relations = relationsByHoldingPerson
				.get(holdingPerson);
		if (relations == null) {
			relations = new HashMap<IRelationType, Set<Person>>();
			relationsByHoldingPerson.put(holdingPerson, relations);
		}
		Set<Person> targetPersons = relations.get(holdingPerson);
		if (targetPersons == null) {
			targetPersons = new HashSet<Person>();
			relations.put(relationType, targetPersons);
		}
		targetPersons.add(targetPerson);
	}

}

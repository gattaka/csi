package org.myftp.gattserver.csi.world;

import java.util.Map;
import java.util.Set;

import org.myftp.gattserver.csi.world.relations.IRelationType;
import org.springframework.stereotype.Service;

@Service
public class World {

	private int population;
	private int numberOfMales;
	private int numberOfFemales;
	private double averageAge;
	private double minimumAge = Double.MAX_VALUE;
	private double maximumAge = 0;

	private Knowledge knowledge;

	private double sumOfAges;

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public Set<IRelationType> getRelationTypes() {
		return knowledge.getRelationsByRelation().keySet();
	}

	public void registerRelation(IRelationType relationType,
			Person holdingPerson, Person targetPerson) {
		knowledge.registerRelation(relationType, holdingPerson, targetPerson);
	}

	public void registerPerson(Person person) {
		population++;
		if (person.isMale()) {
			numberOfMales++;
		} else {
			numberOfFemales++;
		}
		double age = person.getAge();
		sumOfAges += age;
		if (age > maximumAge)
			maximumAge = age;
		if (age < minimumAge)
			minimumAge = age;

		averageAge = sumOfAges / population;
	}

	/**
	 * Gettery
	 */

	public Map<Person, Set<Person>> getPersonsByRelations(IRelationType type) {
		return knowledge.getRelationsByRelation().get(type);
	}

	public int getPopulation() {
		return population;
	}

	public int getNumberOfMales() {
		return numberOfMales;
	}

	public int getNumberOfFemales() {
		return numberOfFemales;
	}

	public double getAverageAge() {
		return averageAge;
	}

	public double getMinimumAge() {
		return minimumAge;
	}

	public double getMaximumAge() {
		return maximumAge;
	}

}

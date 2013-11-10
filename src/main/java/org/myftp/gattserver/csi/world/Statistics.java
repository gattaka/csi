package org.myftp.gattserver.csi.world;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.myftp.gattserver.csi.world.relations.IRelationType;
import org.springframework.stereotype.Service;

@Service
public class Statistics {

	private int population;
	private int numberOfMales;
	private int numberOfFemales;
	private double averageAge;
	private double minimumAge = Double.MAX_VALUE;
	private double maximumAge = 0;

	private Map<IRelationType, Set<Relation>> relationsByType = new HashMap<IRelationType, Set<Relation>>();

	private double sumOfAges;

	public void registerRelation(Relation relation) {
		Set<Relation> relations = relationsByType.get(relation.getType());
		if (relations == null) {
			relations = new HashSet<Relation>();
			relationsByType.put(relation.getType(), relations);
		}
		relations.add(relation);
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

	public Set<Relation> getRelationsByType(IRelationType type) {
		return relationsByType.get(type);
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

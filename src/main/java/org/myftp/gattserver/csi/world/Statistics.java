package org.myftp.gattserver.csi.world;

public enum Statistics {

	INSTANCE;

	private int population;
	private int numberOfMales;
	private int numberOfFemales;
	private double averageAge;
	private double minimumAge = Double.MAX_VALUE;
	private double maximumAge = 0;

	private double sumOfAges;

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

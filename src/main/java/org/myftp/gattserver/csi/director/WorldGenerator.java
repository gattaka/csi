package org.myftp.gattserver.csi.director;

import java.util.Random;
import java.util.Set;

import org.myftp.gattserver.csi.world.Knowledge;
import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorldGenerator {

	private static final int MAX_POPULATION = 50;

	private static Logger logger = LoggerFactory
			.getLogger(WorldGenerator.class);

	public Knowledge generateWorldKnowledge() {

		Knowledge knowledge = new Knowledge();
		Statistics statistics = Statistics.INSTANCE;

		Random random = new Random();

		// I. vygeneruj postavy
		int population = random.nextInt(MAX_POPULATION) + 1;
		Set<Person> persons = knowledge.getPersons();

		logger.info("World population: " + population);

		PersonGenerator personGenerator = new PersonGenerator();
		for (int i = 0; i < population; i++) {
			Person person = personGenerator.generatePerson();
			logger.info("New character: " + person.toString());
			persons.add(person);
			statistics.registerPerson(person);
		}

		return knowledge;
	}
}

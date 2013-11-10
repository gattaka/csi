package org.myftp.gattserver.csi.director;

import java.util.Random;
import java.util.Set;

import org.myftp.gattserver.csi.world.Knowledge;
import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorldGenerator {

	private static final int MAX_POPULATION = 50;
	private static final int MIN_POPULATION = 5;

	@Autowired
	private PersonGenerator personGenerator;

	@Autowired
	private RelationGenerator relationGenerator;

	@Autowired
	private Statistics statistics;

	private static Logger logger = LoggerFactory
			.getLogger(WorldGenerator.class);

	public Knowledge generateWorldKnowledge() {

		Knowledge knowledge = new Knowledge();
		Random random = new Random();

		// I. vygeneruj postavy
		int population = random.nextInt(MAX_POPULATION - MIN_POPULATION)
				+ MIN_POPULATION;
		Set<Person> persons = knowledge.getPersons();

		for (int i = 0; i < population; i++) {
			Person person = personGenerator.generatePerson();
			logger.info("New character: " + person.toString());
			persons.add(person);
			statistics.registerPerson(person);
		}

		// II. vygeneruj vztahy
		// TODO možná generovat opaènì a každému vztahu-typu dát P ve smyslu
		// poèet takových vztahù na 100 lidí
		for (Person holdingPerson : persons) {
			for (Person targetPerson : persons)
				relationGenerator.generateRelation(holdingPerson, targetPerson);
		}

		return knowledge;
	}
}

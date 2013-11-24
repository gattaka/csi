package org.myftp.gattserver.csi.director;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.myftp.gattserver.csi.world.Knowledge;
import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorldGenerator {

	private static final int MAX_BASE_POPULATION = 50;
	private static final int MIN_BASE_POPULATION = 5;

	@Autowired
	private PersonGenerator personGenerator;

	@Autowired
	private RelationGenerator relationGenerator;

	@Autowired
	private World world;

	private static Logger logger = LoggerFactory
			.getLogger(WorldGenerator.class);

	public Knowledge generateWorldKnowledge() {

		Knowledge knowledge = new Knowledge();
		Random random = new Random();

		// I. vygeneruj základní postavy
		int population = random.nextInt(MAX_BASE_POPULATION
				- MIN_BASE_POPULATION)
				+ MIN_BASE_POPULATION;
		List<Person> persons = new ArrayList<>(knowledge.getPersons());

		for (int i = 0; i < population; i++) {
			Person person = personGenerator.generatePerson();
			logger.info("New character: " + person.toString());
			persons.add(person);
			world.registerPerson(person);
		}

		// II. vygeneruj vztahy
		// TODO - vygenerovat generace, dle nich syny a dcery apod.
		// for generation : generations {
		// 1. husband, wife
		// 2. children, siblings...
		// }
		//
		for (Person holdingPerson : persons) {
			relationGenerator.generateRelations(holdingPerson, persons);
		}
		
		// TODO - po všech generacích teprve vygenerovat nìjaké nemorální vztahy apod.
		
		// TODO - nazávìr vygenerovat tìžké vztahy a vraždu

		return knowledge;
	}
}

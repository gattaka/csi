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

	/**
	 * Maximální a minimální poèet dìtí na poèátku historie mìsta - tito lidé
	 * budou procházet vývojem a budou utváøet historii mìsta. U tìchto dìtí se
	 * neøeší rodinné vztahy, protože to budou právì ony, kdo je budou v
	 * budoucnu mezi sebou tvoøit - jde tedy o nezávislý poèáteèní "seed" mìsta.
	 * 
	 * Dìti jsou limitovány vìkem, všechny dìti jsou generovány jako osoby s
	 * minimálním školním vìkem (v intervalu).
	 */
	private static final int MAX_BASE_POPULATION = 50;
	private static final int MIN_BASE_POPULATION = 20;

	private static final int MAX_BASE_AGE = 60;
	private static final int MIN_BASE_AGE = 18;

	@Autowired
	private PersonGenerator personGenerator;

	@Autowired
	private RelationGenerator relationGenerator;

	@Autowired
	private World world;

	private static Logger logger = LoggerFactory.getLogger(WorldGenerator.class);

	public Knowledge generateWorldKnowledge() {

		Random random = new Random();

		// I. vygeneruj základ populace
		int basePopulation = random.nextInt(MAX_BASE_POPULATION - MIN_BASE_POPULATION) + MIN_BASE_POPULATION;
		List<Person> persons = new ArrayList<>(world.getPersons());

		for (int i = 0; i < basePopulation; i++) {
			Person person = personGenerator.generatePerson(MAX_BASE_AGE, MIN_BASE_AGE, 0);
			logger.info("New character (BASE): " + person.toString());
			world.registerPerson(person);
		}

		// II. vztahy
		persons = new ArrayList<>(world.getPersons());
		for (Person holdingPerson : persons) {
			relationGenerator.generateRelations(holdingPerson, persons);
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

		// TODO - po všech generacích teprve vygenerovat nìjaké nemorální vztahy
		// apod.

		// TODO - nazávìr vygenerovat tìžké vztahy a vraždu

		return world.getKnowledge();
	}
}

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
	 * Maxim�ln� a minim�ln� po�et d�t� na po��tku historie m�sta - tito lid�
	 * budou proch�zet v�vojem a budou utv��et historii m�sta. U t�chto d�t� se
	 * ne�e�� rodinn� vztahy, proto�e to budou pr�v� ony, kdo je budou v
	 * budoucnu mezi sebou tvo�it - jde tedy o nez�visl� po��te�n� "seed" m�sta.
	 * 
	 * D�ti jsou limitov�ny v�kem, v�echny d�ti jsou generov�ny jako osoby s
	 * minim�ln�m �koln�m v�kem (v intervalu).
	 */
	private static final int MAX_BASE_POPULATION = 50;
	private static final int MIN_BASE_POPULATION = 20;

	/**
	 * V�k po��te�n� populace
	 */
	private static final int BASE_POPULATION_START_AGE = 7;

	/**
	 * Maxim�ln� po�et p��choz�ch do m�sta - jde o osoby, kter� zpest�uj�
	 * populaci m�sta, aby v n�m nebyly pouze d�ti po��te�n� populace - jejich
	 * v�k se nekontroluje, jedin� podm�nka je, �e nesm� j�t o d�t� (to by do
	 * m�sta samo nep�i�lo)
	 */
	private static final int MAX_INCOMERS_BY_YEAR = 10;

	/**
	 * Maxim�ln� a minim�ln� v�k p��choz�ch lid�
	 */
	private static final int MAX_INCOMER_AGE = 60;
	private static final int MIN_INCOMER_AGE = 20;

	/**
	 * Kolik let historie m�sta bude nasimulov�no, ne� p�ijde sou�asnost a
	 * p��pad s vra�dou
	 */
	private static final int YEARS_OF_PROJECTION = 60;

	@Autowired
	private PersonGenerator personGenerator;

	@Autowired
	private RelationGenerator relationGenerator;

	@Autowired
	private World world;

	private static Logger logger = LoggerFactory
			.getLogger(WorldGenerator.class);

	public Knowledge generateWorldKnowledge() {

		Random random = new Random();

		// I. vygeneruj z�klad populace (d�ti)
		int basePopulation = random.nextInt(MAX_BASE_POPULATION
				- MIN_BASE_POPULATION)
				+ MIN_BASE_POPULATION;
		List<Person> persons = new ArrayList<>(world.getPersons());

		for (int i = 0; i < basePopulation; i++) {
			Person person = personGenerator.generatePerson(
					BASE_POPULATION_START_AGE, YEARS_OF_PROJECTION);
			logger.info("New character (BASE): " + person.toString());
			world.registerPerson(person);
		}

		// II. Simuluj historii m�sta
		for (int yearOffset = YEARS_OF_PROJECTION; yearOffset >= 0; yearOffset--) {

			world.setYearOffset(yearOffset);

			// vygeneruj pro tento rok p�ist�hovalce
			int thisYearIncomers = random.nextInt(MAX_INCOMERS_BY_YEAR);
			for (int i = 0; i < thisYearIncomers; i++) {
				Person person = personGenerator.generatePerson(MAX_INCOMER_AGE,
						MIN_INCOMER_AGE, YEARS_OF_PROJECTION);
				logger.info("New character (INCOMER): " + person.toString());
				world.registerPerson(person);
			}

			// losov�n� p�irozen� smrti (st���, nemoc) ?

			// vztahy
			persons = new ArrayList<>(world.getPersons());
			for (Person holdingPerson : persons) {
				relationGenerator.generateCoupleRelations(holdingPerson,
						persons);
			}

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

		// TODO - po v�ech generac�ch teprve vygenerovat n�jak� nemor�ln� vztahy
		// apod.

		// TODO - naz�v�r vygenerovat t�k� vztahy a vra�du

		return world.getKnowledge();
	}
}

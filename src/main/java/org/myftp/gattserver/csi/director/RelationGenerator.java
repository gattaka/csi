package org.myftp.gattserver.csi.director;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.myftp.gattserver.csi.world.Immorality;
import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.World;
import org.myftp.gattserver.csi.world.relations.IRelationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationGenerator {

	private static Logger logger = LoggerFactory.getLogger(RelationGenerator.class);

	private Map<Immorality, List<IRelationType>> relationsPalette = new HashMap<Immorality, List<IRelationType>>();

	@Autowired(required = false)
	private List<IRelationType> iRelationTypes;

	@Autowired
	private World world;

	@PostConstruct
	public void init() {

		for (Immorality immorality : Immorality.values()) {
			relationsPalette.put(immorality, new ArrayList<IRelationType>());
		}

		for (IRelationType type : iRelationTypes) {
			relationsPalette.get(type.getImmorality()).add(type);
		}

	}

	/**
	 * Generuje vztahy iniciované vybranou osobou. Prochází postupnì všechny
	 * typy vztahù a náhodnì je realizuje na vybrané osobì a náhodnì vybrané
	 * cílové osobì (pøíjemce vztahu)
	 * 
	 * @param holdingPerson
	 * @param persons
	 * @param allowedRelations
	 */
	public void generateRelations(Person holdingPerson, List<Person> persons, IRelationType... allowedRelations) {

		List<IRelationType> relationTypes = allowedRelations.length == 0 ? iRelationTypes : Arrays
				.asList(allowedRelations);
		for (IRelationType relationType : relationTypes) {

			Random random = new Random();
			double hit = random.nextInt(100) / 100.0;

			// náhodnì vyber cílovou osobu
			Person targetPerson = null;
			while (targetPerson == null || holdingPerson.equals(targetPerson) == true) {
				targetPerson = persons.get(random.nextInt(persons.size()));
			}

			// bude vztah na osobách realizován dle pravdìpodobnosti vztahu ?
			if (hit < relationType.getPropability(holdingPerson, targetPerson)) {

				if (relationType.applyRelation(holdingPerson, targetPerson)) {
					logger.info(holdingPerson.getFirstName() + " " + holdingPerson.getSureName() + " --> "
							+ relationType.getName() + " --> " + targetPerson.getFirstName() + " "
							+ targetPerson.getSureName());
					world.registerRelation(relationType, holdingPerson, targetPerson);
				}
			}
		}

	}

}

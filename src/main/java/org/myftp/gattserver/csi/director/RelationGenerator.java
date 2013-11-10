package org.myftp.gattserver.csi.director;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.myftp.gattserver.csi.world.Immorality;
import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.IRelationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationGenerator {

	private Map<Immorality, List<IRelationType>> relationsPalette = new HashMap<Immorality, List<IRelationType>>();

	@Autowired(required = false)
	private List<IRelationType> iRelationTypes;

	@PostConstruct
	public void init() {

		for (Immorality immorality : Immorality.values()) {
			relationsPalette.put(immorality, new ArrayList<IRelationType>());
		}

		for (IRelationType type : iRelationTypes) {
			relationsPalette.get(type.getImmorality()).add(type);
		}

	}

	public void generateRelation(Person holdingPerson, Person targetPerson) {

		Random random = new Random();
		int hit = random.nextInt(100);

		Immorality immorality;
		if (hit < 50) {
			immorality = Immorality.MORAL;
		} else if (hit < 80) {
			immorality = Immorality.SLIGHTLY_IMMORAL;
		} else if (hit < 95) {
			immorality = Immorality.IMMORAL;
		} else {
			immorality = Immorality.VERY_IMMORAL;
		}

		List<IRelationType> relationTypes = relationsPalette.get(immorality);
		IRelationType relationType = relationTypes.get(random
				.nextInt(relationTypes.size()));

		relationType.apply(holdingPerson, targetPerson);

	}
}

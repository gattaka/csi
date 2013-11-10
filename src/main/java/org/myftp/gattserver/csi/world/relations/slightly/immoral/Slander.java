package org.myftp.gattserver.csi.world.relations.slightly.immoral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.Relation;
import org.myftp.gattserver.csi.world.relations.AbstractSlightlyImmoralRelationType;
import org.springframework.stereotype.Component;

/**
 * Pomluva
 * 
 * @author Hynek
 *
 */
@Component
public class Slander extends AbstractSlightlyImmoralRelationType {

	public Slander() {
		super("Slander");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {
		Relation relation = new Relation(holdingPerson, targetPerson, this);
		holdingPerson.getKnowledge().getRelations().add(relation);
		targetPerson.getKnowledge().getRelations().add(relation);
		return true;
	}

}

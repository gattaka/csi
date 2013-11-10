package org.myftp.gattserver.csi.world.relations.slightly.immoral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.Relation;
import org.myftp.gattserver.csi.world.relations.AbstractSlightlyImmoralRelationType;
import org.springframework.stereotype.Component;

@Component
public class Unfriendly extends AbstractSlightlyImmoralRelationType {

	public Unfriendly() {
		super("Unfriendly");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {
		Relation relation = new Relation(holdingPerson, targetPerson, this);
		holdingPerson.getKnowledge().getRelations().add(relation);
		targetPerson.getKnowledge().getRelations().add(relation);

		relation = new Relation(targetPerson, holdingPerson, this);
		targetPerson.getKnowledge().getRelations().add(relation);
		holdingPerson.getKnowledge().getRelations().add(relation);
		return true;
	}

}

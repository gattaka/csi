package org.myftp.gattserver.csi.world.relations.immoral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.Relation;
import org.myftp.gattserver.csi.world.relations.AbstractImmoralRelationType;
import org.springframework.stereotype.Component;

@Component
public class CheatingOn extends AbstractImmoralRelationType {

	public CheatingOn() {
		super("Cheating on");
	}

	public void apply(Person holdingPerson, Person targetPerson) {
		Relation relation = new Relation(holdingPerson, targetPerson, this);
		holdingPerson.getKnowledge().getRelations().add(relation);
		targetPerson.getKnowledge().getRelations().add(relation);
	}

}

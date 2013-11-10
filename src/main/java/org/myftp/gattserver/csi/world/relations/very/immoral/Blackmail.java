package org.myftp.gattserver.csi.world.relations.very.immoral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.Relation;
import org.myftp.gattserver.csi.world.relations.AbstractVeryImmoralRelationType;
import org.springframework.stereotype.Component;

@Component
public class Blackmail extends AbstractVeryImmoralRelationType {

	public Blackmail() {
		super("Blackmail");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {
		Relation relation = new Relation(holdingPerson, targetPerson, this);
		holdingPerson.getKnowledge().getRelations().add(relation);
		targetPerson.getKnowledge().getRelations().add(relation);
		return true;
	}

}

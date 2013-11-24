package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.Relation;
import org.myftp.gattserver.csi.world.relations.AbstractMoralRelationType;
import org.springframework.stereotype.Component;

@Component
public class Brother extends AbstractMoralRelationType {

	public Brother() {
		super("Brother");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {
		if (holdingPerson.isMale() == false)
			return false;

		Relation relation = new Relation(holdingPerson, targetPerson, this);
		holdingPerson.addRelation(relation);
		holdingPerson.getKnowledge().getRelations().add(relation);
		targetPerson.getKnowledge().getRelations().add(relation);
		return true;
	}

}

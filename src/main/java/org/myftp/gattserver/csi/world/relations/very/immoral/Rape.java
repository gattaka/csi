package org.myftp.gattserver.csi.world.relations.very.immoral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.Relation;
import org.myftp.gattserver.csi.world.relations.AbstractVeryImmoralRelationType;
import org.springframework.stereotype.Component;

@Component
public class Rape extends AbstractVeryImmoralRelationType {

	public Rape() {
		super("Rape");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {

		// zatím pomineme extrémní pøípady, že znásilòuje žena :)
		if (holdingPerson.isMale() == false)
			return false;

		// ... øeknìme, že zatím bereme "klasické" znásilnìní, kde obìtí je žena
		if (targetPerson.isMale())
			return false;

		Relation relation = new Relation(holdingPerson, targetPerson, this);
		holdingPerson.addRelation(relation);
		holdingPerson.getKnowledge().getRelations().add(relation);
		targetPerson.getKnowledge().getRelations().add(relation);
		return true;
	}

}

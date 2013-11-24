package org.myftp.gattserver.csi.world.relations.moral;

import java.util.Set;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.Relation;
import org.myftp.gattserver.csi.world.relations.AbstractMoralRelationType;
import org.springframework.stereotype.Component;

@Component
public class Boyfriend extends AbstractMoralRelationType {

	public Boyfriend() {
		super("Boyfriend");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {

		// P��tel mus� b�t mu�
		if (holdingPerson.isMale() == false)
			return false;
		
		// Gayov� odpust� - c�lov� osoba p��tele mus� b�t �ena 
		if (targetPerson.isMale())
			return false;
		
		// Nem��e chodit se svoj� sestrou, matkou (ani jej� matkou nebo sestrou apod.) a nesm� b�t �enat�
		Set<Relation> sisterRelations = targetPerson.getRelationsByType().get(new Sister());
		if () !)

		Relation relation = new Relation(holdingPerson, targetPerson, this);
		holdingPerson.addRelation(relation);
		holdingPerson.getKnowledge().getRelations().add(relation);
		targetPerson.getKnowledge().getRelations().add(relation);
		return true;
	}

}

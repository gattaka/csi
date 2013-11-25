package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Knowledge;
import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractMoralRelationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BreakUp extends AbstractMoralRelationType {

	@Autowired
	private Girlfriend girlfriend;
	@Autowired
	private Boyfriend boyfriend;

	public BreakUp() {
		super("BreakUp");
	}

	@Override
	public double getPropability(Person holdingPerson, Person targetPerson) {
		return 0.5;
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {

		if (holdingPerson.isMale()) {
			if (targetPerson.isMale() == false) {
				if (worldKnowledge.isInRelation(holdingPerson, boyfriend,
						targetPerson) == false)
					return false;
			} else {
				return false;
			}

		} else {
			if (targetPerson.isMale()) {
				if (worldKnowledge.isInRelation(holdingPerson, girlfriend,
						targetPerson) == false)
					return false;
			} else {
				return false;
			}
		}

		holdingPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);
		targetPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);

		// navíc zapiš opaèný vztah
		holdingPerson.getKnowledge().registerRelation(this, targetPerson,
				holdingPerson);
		targetPerson.getKnowledge().registerRelation(this, targetPerson,
				holdingPerson);
		worldKnowledge.registerRelation(this, targetPerson, holdingPerson);

		// navíc zruš vztah boyfriend, girlfriend u obou úèastníkù +
		// worldKnowledge

		Knowledge[] knowledges = new Knowledge[] {
				holdingPerson.getKnowledge(), targetPerson.getKnowledge(),
				worldKnowledge };
		for (Knowledge knowledge : knowledges) {
			knowledge.removeRelation(boyfriend, holdingPerson, targetPerson);
			knowledge.removeRelation(girlfriend, targetPerson, holdingPerson);
		}

		return true;
	}

}

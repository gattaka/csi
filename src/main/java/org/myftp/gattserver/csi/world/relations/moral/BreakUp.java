package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BreakUp extends AbstractCoupleRelationType {

	@Autowired
	private Girlfriend girlfriend;
	@Autowired
	private Boyfriend boyfriend;
	@Autowired
	private Husband husband;
	@Autowired
	private Wife wife;

	public BreakUp() {
		super("BreakUp");
	}

	@Override
	public double getPropability(Person holdingPerson, Person targetPerson) {
		return 0.5;
	}

	protected boolean specificApply(Person holdingPerson, Person targetPerson) {

		// nemùžou být zároveò rozešlí když spolu chodí/jsou manželé
		if (holdingPerson.isMale()) {
			if (worldKnowledge.isInRelation(holdingPerson, boyfriend, targetPerson))
				return false;
			if (worldKnowledge.isInRelation(holdingPerson, husband, targetPerson))
				return false;
			if (Boyfriend.checkFamily(holdingPerson, targetPerson, worldKnowledge) == false)
				return false;
		} else {
			if (worldKnowledge.isInRelation(holdingPerson, girlfriend, targetPerson))
				return false;
			if (worldKnowledge.isInRelation(holdingPerson, wife, targetPerson))
				return false;
			if (Girlfriend.checkFamily(holdingPerson, targetPerson, worldKnowledge) == false)
				return false;
		}

		holdingPerson.getKnowledge().registerRelation(this, holdingPerson, targetPerson);
		targetPerson.getKnowledge().registerRelation(this, holdingPerson, targetPerson);
		// worldKnowledge zapíše parent-class

		// navíc zapiš opaèný vztah
		holdingPerson.getKnowledge().registerRelation(this, targetPerson, holdingPerson);
		targetPerson.getKnowledge().registerRelation(this, targetPerson, holdingPerson);
		worldKnowledge.registerRelation(this, targetPerson, holdingPerson);

		return true;
	}

}

package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.tagging.Aunt;
import org.myftp.gattserver.csi.world.relations.tagging.Cousin;
import org.myftp.gattserver.csi.world.relations.tagging.Mother;
import org.myftp.gattserver.csi.world.relations.tagging.Sister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Boyfriend extends AbstractCoupleRelationType {

	@Autowired
	private Husband husband;
	@Autowired
	private Girlfriend girlfriend;

	public Boyfriend() {
		super("Boyfriend");
	}

	@Override
	protected boolean specificApply(Person him, Person her) {
		Person holdingPerson = him;
		Person targetPerson = her;

		if (worldKnowledge.isInRelation(holdingPerson, husband))
			return false;

		if (worldKnowledge.isInRelation(holdingPerson, this))
			return false;

		if (worldKnowledge.checkBannedRelations(holdingPerson, targetPerson,
				Sister.getInstance(), Mother.getInstance(),
				Cousin.getInstance(), Aunt.getInstance()) == false)
			return false;

		holdingPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);
		targetPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);

		// navíc zapiš opaèný vztah
		holdingPerson.getKnowledge().registerRelation(girlfriend, targetPerson,
				holdingPerson);
		targetPerson.getKnowledge().registerRelation(girlfriend, targetPerson,
				holdingPerson);
		worldKnowledge.registerRelation(girlfriend, targetPerson, holdingPerson);

		return true;
	}
}
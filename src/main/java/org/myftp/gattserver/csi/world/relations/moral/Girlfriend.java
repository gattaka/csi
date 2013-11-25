package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.tagging.Brother;
import org.myftp.gattserver.csi.world.relations.tagging.Cousin;
import org.myftp.gattserver.csi.world.relations.tagging.Father;
import org.myftp.gattserver.csi.world.relations.tagging.Uncle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Girlfriend extends AbstractCoupleRelationType {

	@Autowired
	private Wife wife;
	@Autowired
	private Boyfriend boyfriend;

	public Girlfriend() {
		super("Girlfriend");
	}

	@Override
	protected boolean specificApply(Person him, Person her) {
		Person holdingPerson = her;
		Person targetPerson = him;

		if (worldKnowledge.isInRelation(holdingPerson, wife))
			return false;

		if (worldKnowledge.isInRelation(holdingPerson, this))
			return false;

		if (worldKnowledge.checkBannedRelations(holdingPerson, targetPerson,
				Brother.getInstance(), Father.getInstance(),
				Cousin.getInstance(), Uncle.getInstance()) == false)
			return false;

		holdingPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);
		targetPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);

		// navíc zapiš opaèný vztah
		holdingPerson.getKnowledge().registerRelation(boyfriend, targetPerson,
				holdingPerson);
		targetPerson.getKnowledge().registerRelation(boyfriend, targetPerson,
				holdingPerson);
		worldKnowledge.registerRelation(boyfriend, targetPerson, holdingPerson);

		return true;
	}

}

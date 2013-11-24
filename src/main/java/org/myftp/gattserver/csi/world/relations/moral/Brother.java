package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractMoralRelationType;
import org.myftp.gattserver.csi.world.relations.IRelationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Brother extends AbstractMoralRelationType {

	@Autowired
	private Father father;
	@Autowired
	private Mother mother;
	@Autowired
	private Sister sister;
	@Autowired
	private Brother brother;
	@Autowired
	private Son son;
	@Autowired
	private Daughter daughter;

	public Brother() {
		super("Brother");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {
		if (holdingPerson.isMale() == false)
			return false;

		// nem��u b�t bratr sv�ho otce, matky, dcery ani syna
		IRelationType[] bannedFirstLevelRelations = new IRelationType[] {
				sister, mother, daughter, son };
		// ani jejich otc�, matek, sester, bratr�, dcer a syn� (jedin� syn� a
		// dcer m�ho otce a matky)
		IRelationType[] bannedDeepLevelsRelations = new IRelationType[] {
				sister, mother };
		if (checkRecursivelyBannedRelations(holdingPerson, targetPerson,
				bannedFirstLevelRelations, bannedDeepLevelsRelations, true) == false)
			return false;

		holdingPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);
		targetPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);
		return true;
	}

}

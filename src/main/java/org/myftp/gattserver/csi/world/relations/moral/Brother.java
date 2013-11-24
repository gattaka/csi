package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractFamilyRelationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Brother extends AbstractFamilyRelationType {

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
		
		holdingPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);
		targetPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);
		return true;
	}

}

package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractFamilyRelationType;
import org.springframework.stereotype.Component;

@Component
public class Son extends AbstractFamilyRelationType {

	public Son() {
		super("Son");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {
		// TODO Auto-generated method stub
		return true;
	}

}

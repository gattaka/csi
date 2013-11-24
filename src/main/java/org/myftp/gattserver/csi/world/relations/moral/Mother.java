package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractFamilyRelationType;
import org.springframework.stereotype.Component;

@Component
public class Mother extends AbstractFamilyRelationType {

	public Mother() {
		super("Mother");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {
		// TODO Auto-generated method stub
		return true;
	}

}

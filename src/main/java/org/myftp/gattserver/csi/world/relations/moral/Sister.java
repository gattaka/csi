package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractFamilyRelationType;
import org.springframework.stereotype.Component;

@Component
public class Sister extends AbstractFamilyRelationType {

	public Sister() {
		super("Sister");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {
		// TODO Auto-generated method stub
		return true;
	}

}

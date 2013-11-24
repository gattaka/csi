package org.myftp.gattserver.csi.world.relations.special;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractSpecialRelationType;
import org.springframework.stereotype.Component;

@Component
public class Murder extends AbstractSpecialRelationType {

	public Murder() {
		super("Murder");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {
		// TODO Auto-generated method stub
		return true;

	}

	public double getPropability() {
		return 0;
	}

}

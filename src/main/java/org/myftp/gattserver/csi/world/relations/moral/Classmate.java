package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractMoralRelationType;
import org.springframework.stereotype.Component;

@Component
public class Classmate extends AbstractMoralRelationType {

	public Classmate() {
		super("Boyfriend");
	}

	public void apply(Person holdingPerson, Person targetPerson) {
		// TODO Auto-generated method stub
	}

}

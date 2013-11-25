package org.myftp.gattserver.csi.world.relations.moral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractMoralRelationType;
import org.springframework.stereotype.Component;

@Component
public class Girlfriend extends AbstractMoralRelationType {

	public Girlfriend() {
		super("Girlfriend");
	}

	@Override
	public double getPropability() {
		return 0.3;
	}
	
	public boolean apply(Person holdingPerson, Person targetPerson) {
		// TODO Auto-generated method stub
		return true;
	}

}

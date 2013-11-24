package org.myftp.gattserver.csi.world.relations.very.immoral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractVeryImmoralRelationType;
import org.springframework.stereotype.Component;

@Component
public class Blackmail extends AbstractVeryImmoralRelationType {

	public Blackmail() {
		super("Blackmail");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {
		// TODO
		return true;
	}

}

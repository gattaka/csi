package org.myftp.gattserver.csi.world.relations.slightly.immoral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractSlightlyImmoralRelationType;
import org.springframework.stereotype.Component;

@Component
public class Unfriendly extends AbstractSlightlyImmoralRelationType {

	public Unfriendly() {
		super("Unfriendly");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {
		// TODO
		return true;
	}

}

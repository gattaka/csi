package org.myftp.gattserver.csi.world.relations.slightly.immoral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractSlightlyImmoralRelationType;
import org.springframework.stereotype.Component;

/**
 * Pomluva
 * 
 * @author Hynek
 *
 */
@Component
public class Slander extends AbstractSlightlyImmoralRelationType {

	public Slander() {
		super("Slander");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {
		// TODO
		return true;
	}

}

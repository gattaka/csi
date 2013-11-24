package org.myftp.gattserver.csi.world.relations.very.immoral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractVeryImmoralRelationType;
import org.springframework.stereotype.Component;

@Component
public class Rape extends AbstractVeryImmoralRelationType {

	public Rape() {
		super("Rape");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {

		// zat�m pomineme extr�mn� p��pady, �e zn�sil�uje �ena :)
		if (holdingPerson.isMale() == false)
			return false;

		// ... �ekn�me, �e zat�m bereme "klasick�" zn�siln�n�, kde ob�t� je �ena
		if (targetPerson.isMale())
			return false;

		// TODO
		return true;
	}

}

package org.myftp.gattserver.csi.world.relations.immoral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractImmoralRelationType;
import org.myftp.gattserver.csi.world.relations.moral.Husband;
import org.myftp.gattserver.csi.world.relations.moral.Wife;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheatingOn extends AbstractImmoralRelationType {

	@Autowired
	private Wife wife;

	@Autowired
	private Husband husband;

	public CheatingOn() {
		super("Cheating on");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {

		// podvádìt mùže pouze muž svou žena nebo žena svého muže
		if (holdingPerson.isMale()) {
			if (worldKnowledge.isInRelation(holdingPerson, wife, targetPerson) == false)
				return false;
		} else {
			if (worldKnowledge.isInRelation(holdingPerson, husband,
					targetPerson) == false)
				return false;
		}

		// další kontroly nejsou (ze strany rodiny) provádìny, protože se poèítá
		// s tím, že už pøi vytváøení vztahu wife/husband se kontoluje zda není
		// osoba v jiném konfliktním rodinném vztahu (sestra, matka apod.)

		// manželka neví (vìtšinou), že jí manžel podvádí
		holdingPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);

		return true;
	}

}

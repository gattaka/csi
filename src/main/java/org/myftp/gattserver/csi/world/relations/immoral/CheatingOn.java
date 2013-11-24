package org.myftp.gattserver.csi.world.relations.immoral;

import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.AbstractImmoralRelationType;
import org.myftp.gattserver.csi.world.relations.moral.Husband;
import org.myftp.gattserver.csi.world.relations.moral.Wife;
import org.springframework.stereotype.Component;

@Component
public class CheatingOn extends AbstractImmoralRelationType {

	public CheatingOn() {
		super("Cheating on");
	}

	public boolean apply(Person holdingPerson, Person targetPerson) {

		// podvádìt mùže pouze muž svou žena nebo žena svého muže
		if (holdingPerson.isMale()) {
			if (holdingPerson.get().get(new Wife()) == null)
				return false;
		} else {
			if (holdingPerson.getRelationsByType().get(new Husband()) == null)
				return false;
		}

		// další kontroly nejsou (ze strany rodiny) provádìny, protože se poèítá
		// s tím, že už pøi vytváøení vztahu wife/husband se kontoluje zda není
		// osoba v jiném konfliktním rodinném vztahu (sestra, matka apod.)

		Relation relation = new Relation(holdingPerson, targetPerson, this);
		holdingPerson.addRelation(relation);
		holdingPerson.getKnowledge().getRelations().add(relation);
		// manželka neví (vìtšinou), že jí manžel podvádí
		// targetPerson.getKnowledge().getRelations().add(relation);

		return true;
	}

}

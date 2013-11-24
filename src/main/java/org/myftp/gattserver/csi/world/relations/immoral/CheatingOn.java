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

		// podv�d�t m��e pouze mu� svou �ena nebo �ena sv�ho mu�e
		if (holdingPerson.isMale()) {
			if (holdingPerson.get().get(new Wife()) == null)
				return false;
		} else {
			if (holdingPerson.getRelationsByType().get(new Husband()) == null)
				return false;
		}

		// dal�� kontroly nejsou (ze strany rodiny) prov�d�ny, proto�e se po��t�
		// s t�m, �e u� p�i vytv��en� vztahu wife/husband se kontoluje zda nen�
		// osoba v jin�m konfliktn�m rodinn�m vztahu (sestra, matka apod.)

		Relation relation = new Relation(holdingPerson, targetPerson, this);
		holdingPerson.addRelation(relation);
		holdingPerson.getKnowledge().getRelations().add(relation);
		// man�elka nev� (v�t�inou), �e j� man�el podv�d�
		// targetPerson.getKnowledge().getRelations().add(relation);

		return true;
	}

}

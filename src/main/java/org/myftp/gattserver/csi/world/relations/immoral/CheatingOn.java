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

		// podv�d�t m��e pouze mu� svou �ena nebo �ena sv�ho mu�e
		if (holdingPerson.isMale()) {
			if (worldKnowledge.isInRelation(holdingPerson, wife, targetPerson) == false)
				return false;
		} else {
			if (worldKnowledge.isInRelation(holdingPerson, husband,
					targetPerson) == false)
				return false;
		}

		// dal�� kontroly nejsou (ze strany rodiny) prov�d�ny, proto�e se po��t�
		// s t�m, �e u� p�i vytv��en� vztahu wife/husband se kontoluje zda nen�
		// osoba v jin�m konfliktn�m rodinn�m vztahu (sestra, matka apod.)

		// man�elka nev� (v�t�inou), �e j� man�el podv�d�
		holdingPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);

		return true;
	}

}

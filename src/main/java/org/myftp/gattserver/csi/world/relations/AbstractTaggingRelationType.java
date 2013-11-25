package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Person;
import org.springframework.stereotype.Component;

/**
 * Tyto vztahy slou�� pro lep�� orientaci v�po�t� a navigaci p�i zkoum�n� stav�
 * - nemaj� jin� smysl ne� ozna�ovat vztah - nemaj� aplika�n� podm�nky a nejsou
 * registrov�ny jako spring {@link Component}, tedy je nelze vylosovat
 * 
 * @author Hynek
 */
public abstract class AbstractTaggingRelationType extends
		AbstractMoralRelationType {

	public AbstractTaggingRelationType(String name) {
		super(name);
	}

	protected boolean apply(Person holdingPerson, Person targetPerson) {
		// nen�-li �e�eno jinak o vztahu v�d� oba z��astn�n�
		holdingPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);
		targetPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);
		return true;
	}

}

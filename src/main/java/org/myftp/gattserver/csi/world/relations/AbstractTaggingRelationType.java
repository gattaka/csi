package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Person;
import org.springframework.stereotype.Component;

/**
 * Tyto vztahy slouží pro lepší orientaci výpoètù a navigaci pøi zkoumání stavù
 * - nemají jiný smysl než oznaèovat vztah - nemají aplikaèní podmínky a nejsou
 * registrovány jako spring {@link Component}, tedy je nelze vylosovat
 * 
 * @author Hynek
 */
public abstract class AbstractTaggingRelationType extends
		AbstractMoralRelationType {

	public AbstractTaggingRelationType(String name) {
		super(name);
	}

	protected boolean apply(Person holdingPerson, Person targetPerson) {
		// není-li øeèeno jinak o vztahu vìdí oba zúèastnìní
		holdingPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);
		targetPerson.getKnowledge().registerRelation(this, holdingPerson,
				targetPerson);
		return true;
	}

}

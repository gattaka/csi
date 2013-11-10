package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Immorality;
import org.myftp.gattserver.csi.world.Person;

public interface IRelationType {

	public boolean applyRelation(Person holdingPerson, Person targetPerson);

	public String getName();

	public Immorality getImmorality();

}
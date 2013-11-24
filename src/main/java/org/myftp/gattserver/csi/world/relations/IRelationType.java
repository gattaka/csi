package org.myftp.gattserver.csi.world.relations;

import org.myftp.gattserver.csi.world.Immorality;
import org.myftp.gattserver.csi.world.Person;

public interface IRelationType {

	public boolean applyRelation(Person holdingPerson, Person targetPerson);

	public String getName();

	public Immorality getImmorality();

	/**
	 * Ud�v� pravd�podobnost �e dvojice n�hodn� vybran�ch lid� bude m�t mezi
	 * sebou tenhle vztah
	 * 
	 * @return
	 */
	public double getPropability();

}

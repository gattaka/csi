package org.myftp.gattserver.csi.world.relations.moral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Daughter extends AbstractChild {

	@Autowired
	private Wife wifeRelation;

	@Autowired
	private Husband husbandRelation;

	@Autowired
	private Son sonRelation;

	@Autowired
	private Daughter daughterRelation;

	public Daughter() {
		super("Daughter");
	}

}

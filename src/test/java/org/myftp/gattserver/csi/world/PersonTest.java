package org.myftp.gattserver.csi.world;

import org.junit.Test;
import static org.junit.Assert.*;
import org.myftp.gattserver.csi.director.PersonGenerator;

public class PersonTest {

	@Test
	public void test() {

		PersonGenerator generator = new PersonGenerator();
		Person person = generator.generatePerson();
		System.out.println(person.toString());
		assertTrue(person != null);

	}

}

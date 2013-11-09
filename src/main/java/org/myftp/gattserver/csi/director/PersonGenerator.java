package org.myftp.gattserver.csi.director;

import java.util.Calendar;
import java.util.Random;

import org.myftp.gattserver.csi.palette.Address;
import org.myftp.gattserver.csi.palette.FemaleName;
import org.myftp.gattserver.csi.palette.MaleName;
import org.myftp.gattserver.csi.palette.SureName;
import org.myftp.gattserver.csi.world.Person;

public class PersonGenerator {

	public static Person generatePerson() {

		Random random = new Random();

		Person person = new Person();

		person.setAddress(Address.generateRandomAddress());

		Calendar calendar = Calendar.getInstance();
		int year = Calendar.getInstance().get(Calendar.YEAR)
				- random.nextInt(100) - 1;
		calendar.set(year, random.nextInt(12) + 1, random.nextInt(28) + 1);
		person.setBirthDate(calendar.getTime());

		person.setMale(random.nextBoolean());

		if (person.isMale()) {
			person.setFirstName(MaleName.generateRandom());
		} else {
			person.setFirstName(FemaleName.generateRandom());
		}

		person.setSureName(SureName.generateRandom());

		// nakonec
		person.setFingerprint(person.hashCode());

		return person;
	}

}

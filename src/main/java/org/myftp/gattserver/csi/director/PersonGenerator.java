package org.myftp.gattserver.csi.director;

import java.util.Calendar;
import java.util.Random;

import org.myftp.gattserver.csi.palette.Address;
import org.myftp.gattserver.csi.palette.FemaleName;
import org.myftp.gattserver.csi.palette.MaleName;
import org.myftp.gattserver.csi.palette.SureName;
import org.myftp.gattserver.csi.world.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonGenerator {

	public Person generatePerson(int age, int yearOffset) {
		return generatePerson(age, age, yearOffset);
	}

	public Person generatePerson(int maxAge, int minAge, int yearOffset) {

		Person person = new Person();

		person.setAddress(Address.generateRandomAddress());

		Calendar calendar = Calendar.getInstance();
		Random random = new Random();
		int ageRandom = maxAge == minAge ? 0 : random.nextInt(maxAge - minAge);
		int year = Calendar.getInstance().get(Calendar.YEAR) - ageRandom
				- minAge - yearOffset;
		calendar.set(year, random.nextInt(12) + 1, random.nextInt(28) + 1);
		person.setBirthDate(calendar.getTime());

		person.setMale(random.nextBoolean());

		if (person.isMale()) {
			person.setFirstName(MaleName.generateRandom());
		} else {
			person.setFirstName(FemaleName.generateRandom());
		}

		person.setSureName(SureName.generateRandom());

		return person;
	}

}

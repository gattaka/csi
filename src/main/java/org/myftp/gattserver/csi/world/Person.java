package org.myftp.gattserver.csi.world;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.myftp.gattserver.csi.palette.Address;
import org.myftp.gattserver.csi.palette.FirstName;
import org.myftp.gattserver.csi.palette.SureName;

public class Person {

	private FirstName firstName;
	private SureName sureName;
	private SureName maidenName;
	private Date birthDate;
	private Address address;
	private boolean male;

	private Knowledge knowledge = new Knowledge();

	public FirstName getFirstName() {
		return firstName;
	}

	public void setFirstName(FirstName firstName) {
		this.firstName = firstName;
	}

	public SureName getSureName() {
		return sureName;
	}

	public void setSureName(SureName sureName) {
		this.sureName = sureName;
	}

	public SureName getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(SureName maidenName) {
		this.maidenName = maidenName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public int getFingerprint() {
		return super.hashCode();
	}

	public double getAge() {
		return getAge(0);
	}

	public double getAge(int yearOffset) {
		LocalDate date = LocalDate.now();
		LocalDate birthDate = LocalDate.fromDateFields(this.birthDate);
		double age = Months.monthsBetween(birthDate, date).getMonths() / 12.0 - yearOffset * 12;
		return age;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			return ((Person) obj).getFingerprint() == getFingerprint();
		} else
			return false;
	}

	@Override
	public int hashCode() {
		return getFingerprint();
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.yyyy");
		NumberFormat numberFormat = new DecimalFormat("###.##");
		return "I am " + firstName + " " + sureName + " from " + address + " (" + (male ? "male" : "female")
				+ "), born on " + dateFormat.format(birthDate) + " (age " + numberFormat.format(getAge())
				+ ") - my fingerprint is " + getFingerprint();
	}

}

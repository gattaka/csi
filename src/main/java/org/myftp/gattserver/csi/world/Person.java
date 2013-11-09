package org.myftp.gattserver.csi.world;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

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
	private int fingerprint;

	private Set<Relation> relations;
	private Knowledge knowledge;

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

	public Set<Relation> getRelations() {
		return relations;
	}

	public void setRelations(Set<Relation> relations) {
		this.relations = relations;
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
		return fingerprint;
	}

	public void setFingerprint(int fingerprint) {
		this.fingerprint = fingerprint;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.yyyy");
		return "I am " + firstName + " " + sureName + " from " + address + " ("
				+ (male ? "male" : "female") + "), born on "
				+ dateFormat.format(birthDate) + " - my fingerprint is "
				+ fingerprint;
	}

}

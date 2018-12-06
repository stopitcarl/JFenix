package sth;

import java.util.List;
import java.io.Serializable;

/**
 * Representation of Administrative.
 */
public class Administrative extends Person implements Serializable {

	/**
	 * @param id          Person identifier
	 * @param name        Person's name
	 * @param phoneNumber Person's phone number
	 */
	public Administrative(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
	}

	/**
	 * @return Person's string
	 */
	@Override
	public String accept(PersonVisitor v) {
		return v.showPerson(this);
	}

	@Override
	public String toString() {
		return "FUNCIONÁRIO|" + super.toString();
	}
}

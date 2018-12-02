package sth;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Representation of Person.
 */
public abstract class Person implements Serializable {
	/** Person's name */
	private String _name;
	/* Person's phone number */
	private String _phoneNum;
	/* Person identifier */
	private int _id;
	/* Person's list of notifications */
	private LinkedList<Notification> _notifications;

	/**
	 * @param id Person identifier
	 * @param name Person's name
	 * @param phoneNumber Person's phone number 
	 */
	public Person(int id, String name, String phoneNum) {
		_id = id;
		_name = name;
		_phoneNum = phoneNum;
		_notifications = new LinkedList<Notification>();
	}

	void addNotifications(Notification n) {
		// Do nothing
	}

	/**
	 * Changes person's phone number
	 * @param newPhoneNum
	 */
	public void setPhoneNumber(String newPhoneNum) {
		_phoneNum = newPhoneNum;
	}

	/**
	 * @return Person's phone number
	 */			
	public String getPhoneNumber() {
		return _phoneNum;
	}

	/**
 	* 
 	* @return Person's name
 	*/		
	public String getName() {
		return _name;
	}

	/**	 
	 * @return Person's identifier
	 */
	public int getId() {
		return _id;
	}

	/**	 
	 * @return Person's identifier
	 */
	public abstract String accept(Visitor v);
	
	@Override 
	public boolean equals(Object o) {
		if (o instanceof Person) {
			Person p = (Person) o;
			return _id == p.getId();
		}
		return false;
	}

	@Override 
	public String toString() {
		return _id + "|" + _phoneNum + "|" + _name;
	}
}

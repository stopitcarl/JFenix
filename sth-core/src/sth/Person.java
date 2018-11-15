package sth;

import java.util.ArrayList;

public abstract class Person {
	private String _name;
	private String _phoneNum;
	private int _id;
	private ArrayList<Notification> _notifications;

	public Person(int id, String name, String phoneNum) {
		_id = id;
		_name = name;
		_phoneNum = phoneNum;
		_notifications = new ArrayList<Notification>();
	}

	void addNotifications(Notification n) {
	}

	public void setPhoneNumber(String newPhoneNum) {
		_phoneNum = newPhoneNum;
	}

	public String getPhoneNumber() {
		return _phoneNum;
	}

	public String getName() {
		return _name;
	}

	public int getId() {
		return _id;
	}

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
		return " | " + _id + " | " + " | " + _phoneNum + " | " + _name;
	}
}

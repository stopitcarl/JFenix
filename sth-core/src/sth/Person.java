package sth;

import java.util.*;

import javax.management.Notification;

public abstract class Person {
	private String _name;
	private String _phoneNum;
	private int _id;
	private List < Notification > _notifications;

	public Person(int id, String name, String phoneNum) {		
		_id = id;
		_name = name;
		_phoneNum = phoneNum;
		_notifications = new ArrayList<Notification>();
	}

	void addNotifications(Notification n) {}

	public void setPhoneNumber(String newPhoneNum) {
		_phoneNum = newPhoneNum;
	}

	public String getPhoneNumber() {
		return _phoneNum;
	}
}
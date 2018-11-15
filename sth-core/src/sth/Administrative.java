package sth;

import java.util.List;
import java.io.Serializable;

public class Administrative extends Person implements Serializable{
	private List<Course> _courses;

	public Administrative(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
	}

	@Override 
	public String toString() {
		return "FUNCIONÁRIO|" + super.toString();
	}
}

package sth;

import java.util.*;

public class Project {
	private String _name;
	private String _description;
	private boolean _isOpen;
	private Map<Student, String> _submission;
	private Survey _survey;
	
	public Project(String name) {
		// TODO Auto-generated constructor stub
		_name = name;
		_isOpen = true;
	}

	public String getName() {
		return _name;
	}

	public void closeProject() {
		_isOpen = false;
	}

	public void addNotifications(Notification n) {
		
	}
}

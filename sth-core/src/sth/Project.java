package sth;

import java.util.*;
import java.io.Serializable;

/**
 * Representation of Project.
 */
public class Project implements Serializable {
	/** Project's name */
	private String _name;
	/** Project description */
	private String _description;
	/** Project's state */
	private boolean _isOpen;
	/** Student's submissions */
	private Map<Student, String> _submission;
	/** Project's survey */
	private Survey _survey;
	
	/**
	 * @param name project's name
	 */
	public Project(String name) {
		_name = name;
		_isOpen = true;
	}

	/**
	 * @return project's name
	 */
	public String getName() {
		return _name;
	}

	/** Closes project	*/
	public void closeProject() {
		_isOpen = false;
	}

	/**
	 * @param n
	 */
	public void addNotifications(Notification n) {
		// do nothing
	}
}

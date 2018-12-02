package sth;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Map;
import java.util.Locale;
import java.text.Collator;
import java.util.Collections;

/**
 * Representation of Professor.
 */
public class Professor extends Person implements Serializable {
	
	/** Map of courses and a list of their subjects */
	private Map<Course , TreeMap<String, Subject>> _taughtSubjects;

	/**
	 * @param id person identifier
	 * @param name person's name
	 * @param phoneNumber person's phone number
	 */
	public Professor(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);		 
		_taughtSubjects = new TreeMap<Course, 
						TreeMap<String, Subject>>();
	}

	/**
 	* adds subject to the course's subject list
 	* @param course
 	* @param subject
 	*/
	public void addSubject(Course course, Subject subject) {		
		if (!_taughtSubjects.containsKey(course))
			_taughtSubjects.put(course, new TreeMap<String, Subject>());		
		
		TreeMap<String, Subject> taughtCourse = _taughtSubjects.get(course);
		taughtCourse.put(subject.getName(), subject);
	}

	/**
	 * @return list of courses taught by professor
	 */
	public List<Course> getCourses() {
		return new ArrayList<Course>(_taughtSubjects.keySet());
	}

	/** 
 	* @param disciplineName
 	* @return discipline with name disciplinenames
 	*/
	public Subject getDiscipline(String disciplineName) {		
		for (Map.Entry<Course, TreeMap<String, Subject>> entry : _taughtSubjects.entrySet()) {
			Subject subject = entry.getValue().get(disciplineName);
			if(subject != null && disciplineName.equals(subject.getName()));
				return subject;
		}
		return null;
	}

	/**
	 * @return List of String with course-subject pair
	 */ 
	public List<String> getClasses(){
		LinkedList<String> classes = new LinkedList<String>();

		for (Map.Entry<Course, TreeMap<String, Subject>> entry : _taughtSubjects.entrySet()) {
			Course course = entry.getKey();
			for(String subject : entry.getValue().keySet())
				classes.add("* " + course.getName() + " - " + subject);
		}

		return classes;
	}

	/**	 
	 * @return Person's string
	 */
	public String accept(Visitor v) {
		return v.showPerson(this);
	}

	@Override 
	public String toString() {
		return "DOCENTE|" + super.toString();
	}

	/*
	 * public void createProject(Subject s, String name) { s.addProject(name); }
	 * 
	 * public void closeProject(Subject s, String name) { s.closeProject(name); }
	 */
}

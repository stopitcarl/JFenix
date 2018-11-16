package sth;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.Locale;
import java.text.Collator;
import java.util.Collections;

/**
 * Representation of Professor.
 */
public class Professor extends Person implements Serializable {
	/** Map of courses and a list of their subjects */
	private TreeMap<Course ,LinkedList<Subject>> _taughtSubjects;

	/**
	 * @param id person identifier
	 * @param name person's name
	 * @param phoneNumber person's phone number
	 */
	public Professor(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);		
		_taughtSubjects = new TreeMap<Course ,LinkedList<Subject>>();
	}

	/**
 	* adds subject to the course's subject list
 	* @param course
 	* @param subject
 	*/
	public void addSubject(Course course, Subject subject) {		
		if (!_taughtSubjects.containsKey(course))
			_taughtSubjects.put(course, new LinkedList<Subject>());		
		
		_taughtSubjects.get(course).add(subject);
	}

	/**
	 * @return list of courses taught by professor
	 */
	public List<Course> getCourses() {
		return new LinkedList<Course>(_taughtSubjects.keySet());
	}

	/** 
 	* @param disciplineName
 	* @return discipline with name disciplinename
 	*/
	public Subject getDiscipline(String disciplineName) {
		for (Course course : _taughtSubjects.keySet()){
			for(Subject subject : _taughtSubjects.get(course)){				
				if(disciplineName.equals(subject.getName())){
					return subject;
				}
			}
		}
		return null;
	}

	/**
	 * @return List of String with course-subject pair
	 */ 
	public List<String> getClasses(){
		LinkedList<String> classes = new LinkedList<String>();

		for (Course course : _taughtSubjects.keySet())
			for(Subject subject : _taughtSubjects.get(course))
				classes.add("* " + course.getName() + " - " + subject.getName());
		
		Collections.sort(classes, Collator.getInstance(Locale.getDefault()));
		return classes;
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

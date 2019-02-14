package sth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.Map;
import java.text.Collator;
import java.util.Collections;

/**
 * Representation of Student.
 */
public class Student extends Person implements Serializable {
	
	/** List of subjects */
	private Map<String, Subject> _subjects;
	private Course _course;

	/**
	 * @param id Person identifier
	 * @param name Person's name
	 * @param phoneNumber Person's phone number
	 */
	public Student(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
		_subjects = new TreeMap<String, Subject>();
	}

	/**
	 * Sets student course to course
	 * @param course
	 */
	public void setCourse(Course course) {
		_course = course;
	}

	/**
	 * adds subject to subjects list
	 * @param subject
	 */
	public void addSubject(Subject subject) {
		if (!_subjects.containsKey(subject.getName()))
			_subjects.put(subject.getName(), subject);
	}

	/**
	 * @param course
	 * @return List sorted strings with course - class pair
	 */
	public List<String> getClasses() {		
		ArrayList<String> classes = new ArrayList<String>(_subjects.keySet().size());
		for(String sub : _subjects.keySet())
			classes.add("* " + _course.getName() + " - " + sub);

		return classes;
	}

	/**
	 * @param subject
	 * @return true if subject is enrolled in subject
	 */
	public boolean isEnrolledIn(Subject subject){		
		return _subjects.containsKey(subject.getName());
	}
	
	/*
	public List<String> accept(){

	}

	*/

	/**	 
	 * @return Person's string
	 */
	public String accept(Visitor v) {
		return v.showPerson(this);
	}

	@Override
	public String toString(){
		if (_course.isRepresentative(this.getId()))
			return "DELEGADO|" + super.toString();
		else
			return "ALUNO|" + super.toString();
	}
	
	/*
	 * public void submitSurv(Answer answer, Survey s) {
	 * 
	 * }
	 * 
	 * public void submitProj(String answer, Project p) {
	 * 
	 * }
	 */
}

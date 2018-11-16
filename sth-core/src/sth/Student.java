package sth;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.text.Collator;
import java.util.Collections;

/**
 * Representation of Student.
 */
public class Student extends Person implements Serializable {
	/** List of subjects */
	private List<Subject> _subjects;

	/**
	 * @param id Person identifier
	 * @param name Person's name
	 * @param phoneNumber Person's phone number
	 */
	public Student(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
		_subjects = new LinkedList<Subject>();
	}

	/**
	 * adds subject to subjects list
	 * @param subject
	 */
	public void addSubject(Subject subject) {
		if (!_subjects.contains(subject))
			_subjects.add(subject);
	}

	/**
	 * @param course
	 * @return List sorted strings with course - class pair
	 */
	public List<String> getClasses(Course course) {		
		LinkedList<String> classes = new LinkedList<String>();
		for(Subject sub : _subjects)
			classes.add("* " + course.getName() + " - " + sub.getName());

		Collections.sort(classes, Collator.getInstance(Locale.getDefault()));
		return classes;
	}

	/**
	 * @param subject
	 * @return true if subject is enrolled in subject
	 */
	public boolean isEnrolledIn(Subject subject){
		for (Subject s : _subjects)
			if (s.equals(subject))
				return true;
		return false;
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

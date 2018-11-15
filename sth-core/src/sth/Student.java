package sth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.text.Collator;
import java.util.Collections;


public class Student extends Person implements Serializable {
	private List<Subject> _subjects;
	// private Representative _representative; // TODO: delete this

	public Student(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
		_subjects = new ArrayList<Subject>();
	}

	public void addSubject(Subject subject) {
		if (!_subjects.contains(subject))
			_subjects.add(subject);
	}

	public List<String> getClasses(Course course) {		
		ArrayList<String> classes = new ArrayList<String>();
		for(Subject sub : _subjects)
			classes.add("* " + course.getName() + " - " + sub.getName());

		Collections.sort(classes, Collator.getInstance(Locale.getDefault()));
		return classes;
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

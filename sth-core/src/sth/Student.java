package sth;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
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
		ArrayList<String> subjects = new ArrayList<String>();
		for(Subject sub : _subjects)
			subjects.add("* " + course.getName() + " - " + sub.getName());
		return subjects;
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

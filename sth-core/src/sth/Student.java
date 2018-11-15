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

	@Override 
	public String toString() {
		return "ALUNO" + super.toString();
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

package sth;

import java.util.*;

public class Student extends Person {
	private List<Subject> _subjects;
	private Representative _representative;
	
	public Student(int id, String name, String phoneNumber) {
		// TODO Auto-generated constructor stub
	}
	
	public void addSubject(Subject subject){
		if(!_subjects.contains(subject))
			_subjects.add(subject);
	}

	public void submitSurv(Answer answer, Survey s) {
		
	}
	
	public void submitProj(String answer, Project p) {
		
	}
}

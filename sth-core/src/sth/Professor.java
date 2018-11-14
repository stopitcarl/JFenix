package sth;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Person {
	private List<Course> _courses;
	private List<Subject> _subjects;

	public Professor(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
		_courses = new ArrayList<Course>();
		_subjects = new ArrayList<Subject>();
	}

	public void addSubject(Subject subject) {
		if (!_subjects.contains(subject))
			_subjects.add(subject);
	}

	public void addCourse(Course course) {
		if (!_courses.contains(course))
			_courses.add(course);
	}

	/*
	 * public void createProject(Subject s, String name) { s.addProject(name); }
	 * 
	 * public void closeProject(Subject s, String name) { s.closeProject(name); }
	 */
}

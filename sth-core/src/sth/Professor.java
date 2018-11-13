package sth;

import java.util.*;

public class Professor extends Person {
	private List<Course> _courses;
	private List<Subject> _subjects;
	
	public Professor(int id, String name, String phoneNumber) {
		// TODO Auto-generated constructor stub
	}

	public void addSubjects(Subject subject){
		if(!_subjects.contains(subject))
			_subjects.add(subject);
	}

	public void addCourses(Course course){
		if(!_courses.contains(course))
			_courses.add(course);
	}

	public void createProject(Subject s, String name) {
		
	}
	
	public void closeProject(Subject s, String name) {
		
	}
}

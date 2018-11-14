package sth;

import java.util.List;

public class Course {

	private List<Subject> _subjects;
	private List<Student> _students;
	private String _name;
	private List<Representative> _representatives;

	public Course(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public List<Subject> getSubjects() {
		return _subjects;
	}

	public void addStudent(Student s) {
	}

	public void addSubject(Subject s) {
	}

	public void addRepresentative(Representative s) {
	}

	public void removeRepresentative(Student s) {
	}
}

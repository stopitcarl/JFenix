package sth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



public class Course {
	private static final int MAX_REPRESENTATIVE = 7;
	private List<Subject> _subjects;
	private Map<Integer ,Student> _students; // TODO: change arraylist to array
	private String _name;
	private Student _representatives[];

	public Course(String name) {
		_name = name;
		_subjects = new ArrayList<Subject>();
		_representatives = new Student[MAX_REPRESENTATIVE];
		_students = new TreeMap<Integer, Student>();
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

	public void addRepresentative(Student s) {
		for (int i = 0; i < MAX_REPRESENTATIVE; i++)
			if (_representatives[i] == null) {
				_representatives[i] = s;
				break;
			}
	}

	public void removeRepresentative(Student s) {
	}
}

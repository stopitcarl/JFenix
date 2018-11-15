package sth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



public class Course implements Comparable<Course> {
	private int MAX_REPRESENTATIVE = 7;
	private List<Subject> _subjects;
	private Map<Integer ,Student> _students; // TODO: change arraylist to array
	private String _name;
	private ArrayList<Student> _representatives;

	public Course(String name) {
		_name = name;
		_subjects = new ArrayList<Subject>();
		_representatives = new ArrayList<Student>(MAX_REPRESENTATIVE);
		_students = new TreeMap<Integer, Student>();
	}

	public String getName() {
		return _name;
	}

	public List<Subject> getSubjects() {
		return _subjects;
	}

	public List<Student> getStudents() {
		return new ArrayList<Student>(_students.values());
	} 

	public  Student getStudent(int id) {
		return _students.get(id);				
	} 

	public void addStudent(Student s) {
		if(!_students.containsKey(s.getId()))
			_students.put(s.getId(), s);
	}

	public void addSubject(Subject s) {
	}

	public boolean isRepresentative(Person s){
		for(Student rep: _representatives)
			if(rep.equals(s))
				return true;		
		return false;
	}

	public void addRepresentative(Student s) {
		if(_representatives.size() < MAX_REPRESENTATIVE)
			_representatives.add(s);
	}

	public void removeRepresentative(Student s) {		
			_representatives.remove(s);
	}

	public int compareTo(Course c){
		return _name.compareTo(c.getName());
	}

}

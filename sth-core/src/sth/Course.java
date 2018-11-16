package sth;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Course implements Comparable<Course>,  Serializable {
	/* Maximum number of representatives */
	private int MAX_REPRESENTATIVE = 7;
	/* Maximum number of representatives */
	private int MAX_STUDENTS = 200;
	/** List of subjects */
	private List<Subject> _subjects;
	/** Map of students and their id */
	private Map<Integer ,Student> _students;
	/** Course's name */
	private String _name;
	/** List of representatives */
	private LinkedList<Student> _representatives;

	/**
	 * @param name Course's name 
	 */
	public Course(String name) {
		_name = name;
		_subjects = new LinkedList<Subject>();
		_representatives = new LinkedList<Student>();
		_students = new TreeMap<Integer, Student>();
	}

	/**
 	* @return course name
 	*/
	public String getName() {
		return _name;
	}

	/**
	 * @return list of subjects
	 */
	public List<Subject> getSubjects() {
		return _subjects;
	}


	/**
	 * @return list of students
	 */
	public List<Student> getStudents() {
		return new LinkedList<Student>(_students.values());
	} 

	/** 
	 * @param id
	 * @return Student with the given id
	 */
	public Student getStudent(int id) {
		return _students.get(id);				
	} 

	/**
	 * adds student s to student list
	 * @param s 
	 */
	public void addStudent(Student s) {
		if(_students.size() < MAX_STUDENTS && !_students.containsKey(s.getId()))
			_students.put(s.getId(), s);
	}

	/**
	 * adds subject s to list of subjects
	 * @param s
	 */
	public void addSubject(Subject s) {
		if (!_subjects.contains(s))
			_subjects.add(s);
	}

	/**
 	* @param p
 	* @return true if person is representative
 	*/
	public boolean isRepresentative(Person p){
		for(Student rep: _representatives)
			if(rep.equals(p))
				return true;		
		return false;
	}

	/**
	 * adds student s to representative list
	 * @param s
	 */
	public void addRepresentative(Student s) {
		if(_representatives.size() < MAX_REPRESENTATIVE)
			_representatives.add(s);
	}

	/**
	 * removes student s from representative list
	 * @param s
	 */
	public void removeRepresentative(Student s) {		
			_representatives.remove(s);
	}

	public int compareTo(Course c){
		return _name.compareTo(c.getName());
	}

}

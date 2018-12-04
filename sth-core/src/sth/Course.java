package sth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Course implements Comparable<Course>,  Serializable {
	/* Maximum number of representatives */
	private final static int MAX_REPRESENTATIVE = 7;
	/* Maximum number of representatives */
	private final static int MAX_STUDENTS = 200;
	/** List of subjects */
	private Map<String, Subject> _subjects;
	/** Map of students and their id */
	private Map<Integer ,Student> _students;
	/** Course's name */
	private String _name;
	/** List of representatives */
	private TreeMap<Integer, Student> _representatives;

	/**
	 * @param name Course's name 
	 */
	public Course(String name) {
		_name = name;
		_subjects = new TreeMap<String, Subject>();
		_representatives = new TreeMap<Integer, Student>();
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
		return new ArrayList<Subject>(_subjects.values());
	}

	/**
	 * @return Subject with given name
	 */
	public Subject getSubject(String subjectName) {
		return _subjects.get(subjectName);
	}

	/**
	 * @return list of students
	 */
	public List<Student> getStudents() {
		return new ArrayList<Student>(_students.values());
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
	 * adds subject 's' to list of subjects
	 * @param s
	 */
	public void addSubject(Subject s) {
		if (!_subjects.containsKey(s.getName()))
			_subjects.put(s.getName(), s);
	}

	/**
 	* @param personId
 	* @return true if person is representative of the course
 	*/
	public boolean isRepresentative(int personId){
		if(_representatives.get(personId) != null)
			return true;		
		else
			return false;
	}

	/**
	 * adds student 's' to representative list
	 * @param s
	 */
	public void addRepresentative(Student s) {
		if(_representatives.size() < MAX_REPRESENTATIVE)
			_representatives.put(s.getId(), s);
	}

	/**
	 * removes student 's' from representative list
	 * @param s
	 */
	public void removeRepresentative(int personId) {		
			_representatives.remove(personId);
	}

	public int compareTo(Course c){
		return _name.compareTo(c.getName());
	}

}

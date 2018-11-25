package sth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import sth.exceptions.BadEntryException;
import sth.exceptions.NoSuchPersonIdException;
import sth.exceptions.IllegalDisciplineException;
import sth.exceptions.IllegalProjectNameException;
import sth.exceptions.NoSuchProjectNameException;

/**
 * School implementation.
 */
public class School implements Serializable {
	/** List of courses */
	private List<Course> _courses;
	/** List of professors */
	private List<Professor> _professors;
	/** List of administratives*/
	private List<Administrative> _administratives;

	
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	/**
	 * Initialise the Lists of courses, professors and administratives
	 */
	public School() {
		this._courses = new LinkedList<Course>();
		this._professors = new LinkedList<Professor>();
		this._administratives = new LinkedList<Administrative>();
	}

	/** 
	 * Creates new school from object file 'filename'
	 * @param filename
	 * @throws BadEntryException
	 * @throws IOException
	 */
	public void importFile(String filename) throws IOException, BadEntryException, FileNotFoundException { 
		FileImporter fileIn = new FileImporter();
		fileIn.importFile(filename);
	}

	/**
	 * @param name
	 * @return course with given name
	 */
	private Course searchCourse(final String name) {
		for (Course c : _courses)
			if (c.getName().equals(name))
				return c;
		return null;
	}

	/**
	 * @param course
	 * @param name
	 * @return subject with given name in course
	 */
	private Subject searchSubject(final Course course, final String name) {
		for (Subject s : course.getSubjects())
			if (s.getName().equals(name))
				return s;
		return null;
	}	
	
	/**
	 * @param user
	 * @return an Administrative if user is an administrative
	 */
	public Administrative getAdministrative(Person user){
		// Search administratives
		for (Administrative admin : _administratives)
			if (admin.equals(user))
				return admin;
		return null;
	}

	/**
	 * @param user
	 * @return a Professor if user is a professor
	 */
	public Professor getProfessor(Person user){
		// Search professors
		for (Professor prof : _professors)
			if (prof.equals(user))
				return prof;
		return null;
	}

	/**
	 * @param user
	 * @return a Student if user is a student
	 */
	public Student getStudent(Person user){
		// Search student
		Student s;
		for(Course c : _courses){
			s = c.getStudent(user.getId());
			if(s != null && user.equals(s))
				return s;
		}
		return null;
	}

	/**
 	* @param user
 	* @return true if user is a representative, otherwise false 
 	*/
	public boolean isRepresentative(Person user){
		// Search student
		for(Course c : _courses)			
				if (c.isRepresentative(user))
					return true;
		return false;
	}

	/**
	 * @param name
	 * @return List of Persons with given name
	 */
	public List<Person> searchPerson(String name) {
		LinkedList<Person> matches = new LinkedList<Person>();
		// Search student
		for(Course c : _courses)
			for(Student s : c.getStudents())
				if (s.getName().contains(name))
					matches.add(s);
		// Search professors
		for (Person prof : _professors)
			if (prof.getName().contains(name))
				matches.add(prof);
		// Search administratives
		for (Person admin : _administratives)
			if (admin.getName().contains(name))
				matches.add(admin);
		
		Collections.sort(matches, new PeopleNameSorter());
		return matches;
	}

	/**
	 * @param id
	 * @return Person with given id
	 */
	public Person searchPerson(int id) {
		Person person;
		// search professor list
		for (Professor prof : _professors)
			if (prof.getId() == id)
				return prof;
		// search administrative list
		for (Administrative admin : _administratives)
			if (admin.getId() == id)
				return admin;			
		// search student list
		for (Course c : _courses)	
			if ((person = c.getStudent(id)) != null)
				return person;


		return null;								
	}
	
	/**
	 * @param p
	 * @return String with person's details
	 */
	public String showPerson(Person p) {
		if (getAdministrative(p) != null)
			return p.toString()+"\n";
		
		String info = "";
		Student st;
		Professor prof;
		List<String> classes;

		if((st = getStudent(p)) != null){
			if(isRepresentative(p))
				info += showRepresentative(p)+ "\n";
			else 
				info += showStudent(p) + "\n";
			classes = st.getClasses(getStudentCourse(st));
		} else {
			prof = getProfessor(p);
			info += prof.toString() + "\n";
			classes = prof.getClasses();
		}

		for(String s : classes)
			info += s + "\n";
		
		return info;
	}

	/**
	 * @return List of String with all persons's details
	 */
	public List<String> showAllPersons(){		
		LinkedList<String> people = new LinkedList<String>();
		// Index all professors
		for (Professor prof : _professors)
			people.add(showPerson(prof));				
		
		// Index all administratives
		for (Administrative admin : _administratives)
			people.add(showPerson(admin));				
		
		// Index all students
		for (Course c : _courses)	
			for(Student st : c.getStudents())
				people.add(showPerson(st));
		
		Collections.sort(people, new PeopleIdSorter());
		return people;
	}
	
	/**
	 * @param s
	 * @return course of student
	 */
	private Course getStudentCourse(Student s){
		for (Course c : _courses)
			if (c.getStudent(s.getId()) != null)
				return c;
		return null;			
	}

	/**
	 * @param p
	 * @return String with student info
	 */
	private String showStudent(Person p) {
		return "ALUNO|" + p.toString();
	}

	/**
 	* @param p
 	* @return String with representative info
 	*/
	private String showRepresentative(Person p) {
		return "DELEGADO|" + p.toString();
	}
	
	/**
	 * Creates project in Subject if none exists with same name
	 * @param user
	 * @param subjectName
	 * @param projectName
	 * @throws IllegalDisciplineException
	 * @throws IllegalProjectNameException
	 */
	public void createProject(Person user, String subjectName, String projectName) throws IllegalDisciplineException, IllegalProjectNameException {
		Professor prof = getProfessor(user);
		Subject subject;	
		if((subject = prof.getDiscipline(subjectName)) == null)
			throw new IllegalDisciplineException(subjectName);
		
		subject.addProject(projectName);
	}

	/**
 	* Closes project in subject if one with the given name exists
 	* @param user
 	* @param subjectName
 	* @param projectName
 	* @throws IllegalDisciplineException
 	* @throws NoSuchProjectNameException
 	*/
	public void closeProject(Person user, String subjectName, String projectName) throws IllegalDisciplineException, NoSuchProjectNameException {
		Professor prof = getProfessor(user);
		Subject subject;	
		if((subject = prof.getDiscipline(subjectName)) == null)
			throw new IllegalDisciplineException(subjectName);
		
		subject.closeProject(projectName);
	}


	/**
	 * 
	 * @param user
	 * @param subjectName
	 * @return List of strings with students info that are inrolled in subject
	 * @throws IllegalDisciplineException
	 */
	public List<String> showDisciplineStudents(Person user, String subjectName) throws IllegalDisciplineException{
		Subject subject;			
		LinkedList<String> students = new LinkedList<String>();
		Professor prof = getProfessor(user);
		
		if((subject = prof.getDiscipline(subjectName)) == null)
			throw new IllegalDisciplineException(subjectName);
		
		for(Course c : prof.getCourses()){
			for(Student s : c.getStudents())
				if(s.isEnrolledIn(subject))	
					students.add(showPerson(s));
			break;			
		}
		Collections.sort(students, new PeopleIdSorter());
		return students;
	}

		
	class PeopleIdSorter implements Comparator<String> {
		
		public int compare(String s1, String s2){
			String[] fields1 = s1.split("\\|");
			String[] fields2 = s2.split("\\|");			
			
			if(fields1.length == fields2.length && fields2.length > 1)
				return fields1[1].compareTo(fields2[1]);
			else
				return 0;
		}

	}

	class PeopleNameSorter implements Comparator<Person> {
		
		public int compare(Person p1, Person p2){
			final Collator instance = Collator.getInstance();
			
			// Make collator ignore the accents
    		instance.setStrength(Collator.NO_DECOMPOSITION);
    		
    		return instance.compare(p1.getName(), p2.getName());
		}

	}

	/**  Builds objects from text file */
	class FileImporter{
		transient private Student _tempStudent;
		transient private Professor _tempProf;
		transient private boolean _isRep;

		/**
		* Reads a file and creates appropriate objects
	 	* @param filename
	 	* @throws BadEntryException
	 	* @throws IOException
	 	*/
		public void importFile(String filename) throws IOException, BadEntryException, FileNotFoundException { 
			// exceptions
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
					String[] fields = line.split("\\|");
					registerFromFields(fields);
			}			
			reader.close();
		}

		/**
		 * Pattern matches 'fields' and calls respective factory functions
		 * @param fields
		 * @throws BadEntryException
		 */
		private void registerFromFields(String[] fields) throws BadEntryException {
			// Regular expression pattern to match an agent.
			Pattern patPerson = Pattern.compile("^(ALUNO|DELEGADO|DOCENTE|FUNCIONÁRIO)");
			Pattern patSubject = Pattern.compile("^#.*$");

			if (patPerson.matcher(fields[0]).matches() && fields.length == 4) {
				registerPerson(fields);
			} else if (patSubject.matcher(fields[0]).matches() && fields.length == 2) {
				registerSubject(fields);
			} else {
				throw new BadEntryException(fields.toString());
			}
		}

		/**
		 * Builds a person object from 'fields'
		 * @param fields
		 * @throws BadEntryException
		 */
		private void registerPerson(String[] fields) throws BadEntryException { 
			_tempProf = null;
			_tempStudent = null;

			if(fields == null || fields.length != 4)
				throw new BadEntryException("registerPerson");

			// Read fields
			int id = Integer.parseInt(fields[1]);
			String phoneNum = fields[2];
			String name = fields[3];
			
			if(searchPerson(id) != null)
				throw new BadEntryException(fields[0]);

			// Create objects
			if (fields[0].equals("DELEGADO")) {
				_tempStudent = new Student(id, name, phoneNum);
				_isRep = true;
			} else if (fields[0].equals("ALUNO")) {
				_tempStudent = new Student(id, name, phoneNum);
			} else if (fields[0].equals("DOCENTE")) {
				_tempProf = new Professor(id, name, phoneNum);
				_professors.add(_tempProf);
			} else if (fields[0].equals("FUNCIONÁRIO")) {
				_administratives.add(new Administrative(id, name, phoneNum));
			} else {
				throw new BadEntryException(fields[0]);
			}
		}

		/**
		 * Builds Subject and Course objects from 'fields'
		 * @param fields
		 * @throws BadEntryException
		 */
		private void registerSubject(String[] fields) throws BadEntryException { 
			
			if(fields == null)
				throw new BadEntryException("registerSubject");
			
				fields[0] = fields[0].replaceAll("# ", "");

			Course course;
			Subject subject;

			// Create course if none exists
			if ((course = searchCourse(fields[0])) == null) {
				course = new Course(fields[0]);
				_courses.add(course);
			}

			// Create subject if none exists in that course
			if ((subject = searchSubject(course, fields[1])) == null)
				subject = new Subject(fields[1]);

			course.addSubject(subject);

			if (_isRep) {
				course.addRepresentative(_tempStudent);
				_isRep = false;
			}

			if (_tempStudent != null) {
				course.addStudent(_tempStudent);
				_tempStudent.addSubject(subject);
			} else if (_tempProf != null) {
				_tempProf.addSubject(course, subject);
			}
		}
	}
}

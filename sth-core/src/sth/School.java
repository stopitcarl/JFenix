package sth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

/**
 * School implementation.
 */
public class School implements Serializable {
	private List<Course> _courses;
	private List<Professor> _professors;
	private List<Administrative> _administratives;

	transient private Student _student;
	transient private Professor _professor;
	transient private boolean _isRep;

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	// FIXME implement constructors if needed

	public School() {
		this._courses = new ArrayList<Course>();
		this._professors = new ArrayList<Professor>();
		this._administratives = new ArrayList<Administrative>();
	}

	/**
	 * @param filename
	 * @throws BadEntryException
	 * @throws IOException
	 */
	public void importFile(String filename) throws IOException, BadEntryException, FileNotFoundException { // TODO: fix
																											// exceptions
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split("\\|");
				registerFromFields(fields);
			}
		} catch (FileNotFoundException fnfe) {
			throw fnfe;
		} catch (BadEntryException bex) {
			throw bex;
		} catch (IOException e) {
			throw e;
		}
		reader.close();
	}

	private void registerFromFields(String[] fields) throws BadEntryException { // TODO: Different Exceptions
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

	private void registerPerson(String[] fields) throws BadEntryException { // TODO: find suitable exception
		_professor = null;
		_student = null;

		// TODO: Arg check

		// Read fields
		int id = Integer.parseInt(fields[1]);
		String phoneNum = fields[2];
		String name = fields[3];

		// Create objects
		if (fields[0].equals("DELEGADO")) {
			_student = new Student(id, name, phoneNum);
			_isRep = true;
		} else if (fields[0].equals("ALUNO")) {
			_student = new Student(id, name, phoneNum);
		} else if (fields[0].equals("DOCENTE")) {
			_professor = new Professor(id, name, phoneNum);
			_professors.add(_professor);
		} else if (fields[0].equals("FUNCIONÁRIO")) {
			_administratives.add(new Administrative(id, name, phoneNum));
		} else {
			throw new BadEntryException(fields[0]);
		}
	}

	private void registerSubject(String[] fields) throws BadEntryException { // TODO: fix this exception
		// TODO: Arg check
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
			course.addRepresentative(_student);
			_isRep = false;
		}

		if (_student != null) {
			course.addStudent(_student);
			_student.addSubject(subject);
		} else if (_professor != null) {
			_professor.addSubject(course, subject);
		}
	}

	private void addStudentToCourse(Course c) {
		if (_student != null)
			c.addStudent(_student);
	}

	private Course searchCourse(final String name) {
		for (Course c : _courses)
			if (c.getName().equals(name))
				return c;
		return null;
	}

	private Subject searchSubject(final Course course, final String name) {
		for (Subject s : course.getSubjects())
			if (s.getName().equals(name))
				return s;
		return null;
	}	

	// #############################################################################
	// #############################################################################

	public Administrative getAdministrative(Person user){
		// Search administratives
		for (Administrative admin : _administratives)
			if (admin.equals(user))
				return admin;
		return null;
	}

	public Professor getProfessor(Person user){
		// Search professors
		for (Professor prof : _professors)
			if (prof.equals(user))
				return prof;
		return null;
	}

	public Student getStudent(Person user){
		// Search student
		for(Course c : _courses)
			for(Student s : c.getStudents())
				if(user.equals(s))
					return s;
		return null;
	}

	public boolean isRepresentative(Person user){
		// Search student
		for(Course c : _courses)			
				if (c.isRepresentative(user))
					return true;
		return false;
	}

	// FIXME implement other methods
	public List<Person> searchPerson(String name) {
		ArrayList<Person> matches = new ArrayList<Person>();
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

		return matches;
	}

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

	public List<String> showAllPersons(){		
		ArrayList<String> people = new ArrayList<String>();
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
		
		Collections.sort(people, new PeopleSorter());
		return people;
	}
	

	private Course getStudentCourse(Student s){
		for (Course c : _courses)
			if (c.getStudent(s.getId()) != null)
				return c;
		return null;			
	}


	private String showStudent(Person p) {
		return "ALUNO|" + p.toString();
	}
	private String showRepresentative(Person p) {
		return "DELEGADO|" + p.toString();
	}

	private String getStudentDisciplines(Person p) {
		return "DELEGADO|" + p.toString();
	}

	private String getProfessorDisciplines(Professor p) {
		return "DELEGADO|" + p.toString();
	}

	public void createProject(Person user, String subjectName, String projectName) throws IllegalDisciplineException, IllegalProjectNameException {
		Professor prof = getProfessor(user);
		Subject subject;	
		if((subject = prof.getDiscipline(subjectName)) == null)
			throw new IllegalDisciplineException(subjectName);
		
		subject.addProject(projectName);
	}

	
	
	class PeopleSorter implements Comparator<String> {
		
		public int compare(String s1, String s2){
			String[] fields1 = s1.split("\\|");
			String[] fields2 = s2.split("\\|");			
			
			if(fields1.length == fields2.length && fields2.length > 1)
				return fields1[1].compareTo(fields2[1]);
			else
				return 0;
		}

	}

}

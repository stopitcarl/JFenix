package sth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Pattern;
import sth.exceptions.BadEntryException;
import sth.exceptions.NoSuchPersonIdException;


//FIXME import other classes if needed

/**
 * School implementation.
 */
public class School implements Serializable {
	private List<Course> _courses;
	private List<Professor> _professors;
	private List<Administrative> _administratives;

	private Student _student;
	private Professor _professor;
	private boolean _isRep;

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	// FIXME define object fields (attributes and, possibly, associations)

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
	public void importFile(String filename) throws IOException, BadEntryException, FileNotFoundException { // TODO: fix exceptions
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

		// Arg check		
		for (String s : fields)
			System.out.println(s);

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
			// registerNewStudent()
		} else if (fields[0].equals("DOCENTE")) {
			_professor = new Professor(id, name, phoneNum);
			// registerNewStudent()
		} else if (fields[0].equals("FUNCIONÁRIO")) {
			_administratives.add(new Administrative(id, name, phoneNum));
		} else {
			throw new BadEntryException(fields[0]);
		}
	}

	private void registerSubject(String[] fields) throws BadEntryException { // TODO: fix this exception
		// Arg check		
		fields[0] = fields[0].replaceAll("# ", "");
		

		Course course;
		Subject subject;

		// Create course if none exists
		if ((course = searchCourse(fields[0])) == null)
			course = new Course(fields[0]);		

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
			_professor.addCourse(course);
			_professor.addSubject(subject);
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

	// FIXME implement other methods
	public Person searchPerson(String name) {
		return null;
	}

	public Person searchPerson(int id) {
		return null;
	}

	public List<Person> getAllPeople() {
		return null;
	}
}

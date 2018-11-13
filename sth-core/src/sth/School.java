package sth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.io.IOException;
import java.util.List;
import java.util.regex.*;
import sth.exceptions.BadEntryException;
import sth.exceptions.InvalidCourseSelectionException;
import sth.exceptions.NoSuchPersonIdException;


//FIXME import other classes if neededededed

/**
 * School implementation.
 */
public class School implements Serializable {
	private List<Course> _courses;
	private List<Professor> _professors;
	private List<Administrative> _administrative;
	private Student _student;
	private Professor _Professor;

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	// FIXME define object fields (attributes and, possibly, associations)

	// FIXME implement constructors if needed

	/**
	 * @param filename
	 * @throws BadEntryException
	 * @throws IOException
	 */
	void importFile(String filename) throws IOException, BadEntryException { // TODO: Throw apropriate exceptions
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] fields = line.split("\\|");
			try {
				registerFromFields(fields);
			} catch (Exception e) {
				System.err.printf("WARNING: unknown data %s\n", e.getMessage());
				e.printStackTrace();
			}
		}
		reader.close();
	}

	private void registerFromFields(String[] fields) throws Exception { // TODO: Different Exceptions
		// Regular expression pattern to match an agent.
		Pattern patPerson = Pattern.compile("^(ALUNO|DELEGADO|DOCENTE|FUNCIONÃ�RIO)");
		Pattern patSubject = Pattern.compile("^(#)");

		if (patPerson.matcher(fields[0]).matches()) {
			registerPerson(fields);
		} else if (patSubject.matcher(fields[0]).matches()) {
			registerSubject(fields);
		} /*
			 * else if (parPublication.matcher(fields[0]).matches()) {
			 * registerPublication(fields); } else if
			 * (parConnection.matcher(fields[0]).matches()) { registerConnection(fields); }
			 * else { throw new UnknownDataException(fields[0]); }
			 */
	}

	private void registerPerson(String[] fields) throws Exception { // TODO: find good exception
		// Arg check
		for (String s : fields)
			if (s == null)
				throw new Exception();

		// Read fields
		int id = Integer.parseInt(fields[1]);
		String phoneNum = fields[2];
		String name = fields[3];

		// Create objects
		if (fields[0].equals("DELEGADO")) {
			Student student = new Student(id, name, phoneNum);
			Representative rep = new Representative(id);
		} else if (fields[0].equals("ALUNO")) {
			Student student = new Student(id, name, phoneNum);
			// registerNewStudent()
		} else if (fields[0].equals("DOCENTE")) {
			Professor student = new Professor(id, name, phoneNum);
			// registerNewStudent()
		} else if (fields[0].equals("FUNCIONÃ�RIO")) {
			Administrative student = new Administrative(id, name, phoneNum);
			// registerNewStudent()
		}
	}

	private void registerSubject(String[] fields) throws Exception { // TODO: fix this exception
		// Arg check
		for (String s : fields)
			if (s == null)
				throw new Exception();

		Course course;
		Subject subject;
		if ((course = searchCourse(fields[0])) == null)
			course = new Course(fields[0]);

		if ((subject = searchSubject(course, fields[1])) == null)
			subject = new Subject(fields[1]);

		course.addSubject(subject);
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

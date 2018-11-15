package sth;

import java.util.*;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import sth.exceptions.BadEntryException;
import sth.exceptions.ImportFileException;
import sth.exceptions.NoSuchPersonIdException;

//FIXME import other classes if needed

/**
 * The façade class.
 */
public class SchoolManager {

	// FIXME add object attributes if needed
	private School _school;
	private Person _user;
	private String _fileName;

	// FIXME implement constructors if needed
	public SchoolManager(){
		_school = new School();
	}

	/**
	 * @param datafile
	 * @throws ImportFileException
	 * @throws InvalidCourseSelectionException
	 */
	public void importFile(String datafile) throws ImportFileException {
		try {
			_school.importFile(datafile);			
		} catch (IOException | BadEntryException e) {
			throw new ImportFileException(e);
		}
	}

	/**
	 * @param datafile
	 * @throws FileNotFoundException
	 * @throws NoSuchPersonIdException
	 * @throws NoSuchPersonIdException
	 */
	public void importSchoolFile(String filename) throws FileNotFoundException, ClassNotFoundException, IOException, NoSuchPersonIdException {
		ObjectInputStream reader = new ObjectInputStream(
										new BufferedInputStream(
											new FileInputStream(filename)));
		School newSchool = (School)reader.readObject();
		login(_user.getId());
		_school = newSchool;
		_fileName = filename;
	}

	
	/**
	 * @param id
	 * @throws NoSuchPersonIdException
	 */
	public void login(int id) throws NoSuchPersonIdException {
		if ((_user = searchPerson(id)) == null)
			throw new NoSuchPersonIdException(id);
		
	}

	/**
	 * @return true when the currently logged in person is an administrative
	 */
	public boolean hasAdministrative() {		
		return _school.getAdministrative(_user) != null;
	}

	/**
	 * @return true when the currently logged in person is a professor
	 */
	public boolean hasProfessor() {
		return _school.getProfessor(_user) != null;
	}

	/**
	 * @return true when the currently logged in person is a student
	 */
	public boolean hasStudent() {
		return _school.getStudent(_user) != null;
	}

	/**
	 * @return true when the currently logged in person is a representative
	 */
	public boolean hasRepresentative() {
		return _school.isRepresentative(_user) ;
	}

	// FIXME implement other methods (in general, one for each command in sth-app)
	public void changePhoneNumber(String num) {		
		_user.setPhoneNumber(num);
	}

	public String searchPerson(String name) {		
		String results = "";
		for(Person p: _school.searchPerson(name))
			results += _school.showPerson(p);

		return results;
	}

	public Person searchPerson(int id) {
		return _school.searchPerson(id);										
	}

	public void showAllPersons() {

	}

	public String showPerson() {
		return _school.showPerson(_user);
	}


	public String showPerson(int id) {
		return "";
	}

	public void answerSurvey(String subjectName, String projectName, int hours, String comment) {

	}

	public void deliverProject(String subjectName, String projectName, String answer) {

	}

	public List<Answer> showSurveyResults(String subjectName, String projectName) {
		return null;
	}

	public void closeProject(String subjectName, String projectName) {

	}

	public void createProject(String subjectName, String projectName) {

	}

	public List<Student> showDisciplineStudents(String subjectName) {
		return null;
	}

	public Map<Student, String> showProjectSubmissions(String subjectName, String projectName) {
		return null;
	}

	public void cancelSurvey(String subjectName, String projectName) {

	}

	public void closeSurvey(String subjectName, String projectName) {

	}

	public void createSurvey(String subjectName, String projectName) {

	}

	public void finishSurvey(String subjectName, String projectName) {

	}

	public void openSurvey(String subjectName, String projectName) {

	}

	public List<Survey> showDisciplineSurvey(String subjectName) {
		return null;
	}

	public void save(String filename) {

	}

	public void open(String filename) {

	}
}

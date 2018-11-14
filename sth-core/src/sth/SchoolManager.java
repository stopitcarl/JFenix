package sth;

import java.util.*;
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
	 * @throws ImportFileException
	 * @throws InvalidCourseSelectionException
	 */
	public void importNewFile(String datafile) throws FileNotFoundException, ImportFileException {
		try {
			_school.importFile(datafile);
		} catch (FileNotFoundException ex) {
			throw ex;
		} catch (IOException | BadEntryException e) {
			throw new ImportFileException(e);
		}
	}

	/**
	 * @param id
	 * @throws NoSuchPersonIdException
	 */
	public void login(int id) throws NoSuchPersonIdException {
		// FIXME implement method
	}

	/**
	 * @return true when the currently logged in person is an administrative
	 */
	public boolean hasAdministrative() {
		// FIXME implement predicate
		return false;
	}

	/**
	 * @return true when the currently logged in person is a professor
	 */
	public boolean hasProfessor() {
		// FIXME implement predicate
		return false;
	}

	/**
	 * @return true when the currently logged in person is a student
	 */
	public boolean hasStudent() {
		// FIXME implement predicate
		return false;
	}

	/**
	 * @return true when the currently logged in person is a representative
	 */
	public boolean hasRepresentative() {
		// FIXME implement predicate
		return false;
	}

	// FIXME implement other methods (in general, one for each command in sth-app)
	public void changePhoneNumber(String num) {		
		_user.setPhoneNumber(num);
	}

	public List<Person> searchPerson(String name) { // TODO: is this a supposed to be a list?
		return null;
	}

	public void showAllPersons() {

	}

	public void showPerson(int id) {

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

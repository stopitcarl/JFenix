package sth;

import java.util.Map;
import java.util.List;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import sth.exceptions.BadEntryException;
import sth.exceptions.ImportFileException;
import sth.exceptions.NoSuchPersonIdException;
import sth.exceptions.IllegalDisciplineException;
import sth.exceptions.IllegalProjectNameException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.NoSuchPersonIdException;
import sth.exceptions.TooManySurveysException;


/**
 * The façade class.
 */
public class SchoolManager {
	
	/** School */
	private School _school;
	/** Logged in user */		
	private Person _user;
	/** File to be imported */	
	private String _fileName;

	public SchoolManager(){
		_school = new School();
	}

	/**
	 * Loads school data (students, professors, etc) from text file
	 * @param datafile
	 * @throws ImportFileException
	 */
	public void importFile(String datafile) throws ImportFileException {
		try {
			_school.importFile(datafile);			
		} catch (IOException | BadEntryException e) {
			throw new ImportFileException(e);
		}
	}

	/**
	 * Load school object from object file	
	 * @param filename
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws NoSuchPersonIdException
	 */
	public void importSchoolFile(String filename) throws FileNotFoundException, ClassNotFoundException, IOException, NoSuchPersonIdException {
		
		ObjectInputStream reader = new ObjectInputStream(
										new BufferedInputStream(
											new FileInputStream(filename)));
		School oldSchool = _school; // Save old school

		/** Change active school only if:
		 *  reader can read a valid school && login succeeds */
		try{ 
			School newSchool = (School)reader.readObject();							
			reader.close();
			_school = newSchool;	
			login(_user.getId());
			_fileName = filename;
		} catch(NoSuchPersonIdException e){
			_school = oldSchool;			
			throw e;			
		}		
	}

	
	/**
	 * @param id
	 * @throws NoSuchPersonIdException if user with given id is not in the school
	 */
	public void login(int id) throws NoSuchPersonIdException {
		Person newUser;
		if ((newUser = searchPerson(id)) != null)		
			_user = newUser;
		else
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

	/**
	 * @param num New phone number
	 */
	public void changePhoneNumber(String num) {		
		_user.setPhoneNumber(num);
	}

	/**	 
	 * @param name
	 * @return String with with details on all the people whose name matches 'name'
	 */
	public String searchPerson(String name) {		
		String results = "";
		for(Person p: _school.searchPerson(name))
			results += _school.showPerson(p);

		return results;
	}

	/**
	 * @param id
	 * @return Person with given id
	 */
	public Person searchPerson(int id) {
		return _school.searchPerson(id);										
	}

	/**
	 * @return List of String with all persons's details
	 */
	public String showAllPersons() {
		String people = "";
		for(String s : _school.showAllPersons())
			people += s;
		return people;		 
	}

	/**
 	* 
 	* @return String with person's details
 	*/		
	public String showPerson() {
		return _school.showPerson(_user);
	}

	/**
 	* 
 	* @param subjectName
 	* @param projectName
 	* @param hours
 	* @param comment
 	*/		
	public void answerSurvey(String subjectName, String projectName, int hours, String comment) {
		// do nothing
	}

	/**
 	* 
 	* @param subjectName
 	* @param projectName
 	* @param answer
 	*/		
	public void deliverProject(String subjectName, String projectName, String answer) {
		// do nothing
	}

	/**
	 * 
	 * @param subjectName
	 * @param projectName
	 * @return
	 */		
	public List<Answer> showSurveyResults(String subjectName, String projectName) {
		return null;
	}

	/**
	 * Closes project with given projectName from subject with given subjectName
	 * @param subjectName
	 * @param projectName
	 * @throws IllegalDisciplineException
	 * @throws NoSuchProjectNameException
	 */
	public void closeProject(String subjectName, String projectName)throws IllegalDisciplineException, NoSuchProjectNameException {
		_school.closeProject(_user, subjectName, projectName);
	}

	/**
	 * Creates project with given projectName from subject with given subjectName
	 * @param subjectName
	 * @param projectName
	 * @throws IllegalDisciplineException
	 * @throws IllegalProjectNameException
	 */
	public void createProject(String subjectName, String projectName) throws IllegalDisciplineException, IllegalProjectNameException {
		_school.createProject(_user, subjectName, projectName);
	}

	/**
	 * @param subjectName
	 * @return String of students enrolled in the subject with given subjectName
	 * @throws IllegalDisciplineException
	 */
	public String showDisciplineStudents(String subjectName) throws  IllegalDisciplineException {
		String students = "";
		for(String s : _school.showDisciplineStudents(_user, subjectName))
			students += s;
		return students;
	}

	/**
 	* 
 	* @param subjectName
 	* @param projectName
 	* @return
 	*/	
	public Map<Student, String> showProjectSubmissions(String subjectName, String projectName) {	
		return null;
	}

	/**
	 * 
	 * @param subjectName
	 * @param projectName
	 */				
	public void cancelSurvey(String subjectName, String projectName) 
							throws IllegalDisciplineException, NoSuchProjectNameException, TooManySurveysException {		
		_school.cancelSurvey(_user, subjectName, projectName);
	}

	/**
	 * 
	 * @param subjectName
	 * @param projectName
	 */
	public void closeSurvey(String subjectName, String projectName) {
		//	do nothing		
	}

	/**
	 * 
	 * @param subjectName
	 * @param projectName
	 */		
	public void createSurvey(String subjectName, String projectName) 
							throws IllegalDisciplineException, NoSuchProjectNameException, TooManySurveysException {		
		_school.createSurvey(_user, subjectName, projectName);
	}
	/**
	 * 
	 * @param subjectName
	 * @param projectName
	 */
	public void finishSurvey(String subjectName, String projectName) {
		// do nothing		
	}
	/**
	 * 
	 * @param subjectName
	 * @param projectName
	 */
	public void openSurvey(String subjectName, String projectName) {
		// do nothing		
	}

	/**
	 * 
	 * @param subjectName
	 * @return
	 */
	public List<Survey> showDisciplineSurvey(String subjectName) {	
		return null;
	}

	/**
 	* 
 	* @return true if a filename is already associated
 	*/
	public boolean hasFileName(){
		return _fileName != null;
	}

	/**
 	* write school object to file
 	* @throws FileNotFoundException
 	* @throws IOException
 	*/
	public void save() throws FileNotFoundException, IOException {
		ObjectOutputStream writer = new ObjectOutputStream(
										new BufferedOutputStream(
											new FileOutputStream(_fileName)));
		writer.writeObject(_school);
		writer.close();
	}

	/**
 	* Associates filename to object and saves school object to that file
 	* @param filename
 	* @throws FileNotFoundException
 	* @throws IOException
 	*/
	public void save(String filename) throws FileNotFoundException, IOException  {		
		_fileName = filename;
		save();
	}
	
}

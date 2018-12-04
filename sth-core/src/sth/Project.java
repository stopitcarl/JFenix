package sth;

import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;
import sth.exceptions.TooManySurveysException;
import sth.exceptions.NoSuchSurveyException;
import sth.exceptions.SurveyFinishingException;
import sth.exceptions.SurveyNotEmptyException;
import sth.exceptions.SurveyCancelingException;
import sth.exceptions.SurveyOpeningException;
import sth.exceptions.SurveyClosingException;
import sth.exceptions.SurveyFinishingException;

/**
 * Representation of Project.
 */
public class Project implements Serializable {
	/** Project's name */
	private String _name;
	/** Project description */
	private String _description;
	/** Project's state */
	private boolean _isOpen;
	/** Student's submissions */
	private Map<Integer, String> _submissions;
	/** Project's survey */
	private Survey _survey;
	
	/**
	 * @param name project's name
	 */
	public Project(String name) {
		_name = name;
		_isOpen = true;
		_submissions = new TreeMap<Integer, String>();
	}

	/**
	 * @return project's name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @return true if project is open
	 */
	public boolean isOpen() {
		return _isOpen;
	}

	/** Closes project	*/
	public void closeProject() {	// TODO: add exceptions
		_isOpen = false;
		//openSurvey(); // TODO: uncomment this and look at todo above.. and do it
	}

	public void submitAnswer(int id, String answer) {
		_submissions.put(id, answer);
	}

	public boolean hasSubmission(int id){
		return _submissions.containsKey(id);
	}


	/** Get map of submissions */
	private Map<Integer, String> getSubmissions() {
		return _submissions;
	}

	public void createSurvey() throws TooManySurveysException{
		if (_survey != null)
			throw new TooManySurveysException();			
		else
			_survey = new Survey();
	}

	public void cancelSurvey() throws NoSuchSurveyException, SurveyCancelingException {
		if (_survey != null){
			_survey.cancel();
			_survey = null;
		} else
			throw new NoSuchSurveyException();
	}

	public void openSurvey() throws NoSuchSurveyException, SurveyOpeningException {
		if (_survey != null){
			_survey.open();
		} else
			throw new NoSuchSurveyException();
	}

	public void closeSurvey() throws NoSuchSurveyException, SurveyClosingException {
		if (_survey != null){
			_survey.close();
		} else
			throw new NoSuchSurveyException();
	}

	public void finishSurvey() throws NoSuchSurveyException, SurveyFinishingException {
		if (_survey != null){
			_survey.finalise();
		} else
			throw new NoSuchSurveyException();
	}

	public String getSurveyStatus() {
		if (_survey == null)		
			return "";
		return _survey.getStatus();
	}

	public Survey getSurvey(){
		return _survey;
	}

}

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

	/** Closes project */
	public void closeProject() throws SurveyOpeningException {
		_isOpen = false;
		if (_survey != null)
			_survey.open();
	}

	public void submitAnswer(int id, String answer) {
		_submissions.put(id, answer);
	}

	public boolean hasSubmission(int id) {
		return _submissions.containsKey(id);
	}

	/** Get map of submissions */
	public Map<Integer, String> getSubmissions() {
		return _submissions;
	}

	/** Get number of submissions */
	public int getSubmissionSize() {
		return _submissions.size();
	}

	public void createSurvey(Subject s) throws TooManySurveysException {
		if (_survey != null)
			throw new TooManySurveysException();
		else
			_survey = new Survey(s, this);
	}

	public void cancelSurvey() throws NoSuchSurveyException, SurveyCancelingException {
		if (_survey != null) {
			if (_survey.cancel())
				_survey = null;
		} else
			throw new NoSuchSurveyException();
	}

	public void openSurvey() throws NoSuchSurveyException, SurveyOpeningException {
		if (_survey != null) {
			if (isOpen())
				throw new SurveyOpeningException();
			else
				_survey.open();
		} else
			throw new NoSuchSurveyException();
	}

	public void closeSurvey() throws NoSuchSurveyException, SurveyClosingException {
		if (_survey != null) {
			_survey.close();
		} else
			throw new NoSuchSurveyException();
	}

	public void finishSurvey() throws NoSuchSurveyException, SurveyFinishingException {
		if (_survey != null) {
			_survey.finalise();
		} else
			throw new NoSuchSurveyException();
	}

	public String getSurveyStatus(SurveyShower shower) {
		if (_survey == null)
			return "";
		return _survey.getStatus(shower);
	}

	public Survey getSurvey() {
		return _survey;
	}

}

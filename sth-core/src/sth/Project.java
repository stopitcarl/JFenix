package sth;

import java.util.Map;
import java.util.TooManyListenersException;
import java.util.TreeMap;
import java.io.Serializable;
import sth.exceptions.TooManySurveysException;

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
	private Map<Student, String> _submissions;
	/** Project's survey */
	private Survey _survey;
	
	/**
	 * @param name project's name
	 */
	public Project(String name) {
		_name = name;
		_isOpen = true;
		_submissions = new TreeMap<Student, String>();
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
	public void closeProject() {
		_isOpen = false;
	}

	public void createSurvey() throws TooManySurveysException{
		if (_survey != null)
			throw new TooManySurveysException();			
		else
			_survey = new Survey();
	}

	public Survey getSurvey(){
		return _survey;
	} 
}

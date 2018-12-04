package sth;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.Serializable;

import sth.exceptions.SurveyClosingException;
import sth.exceptions.SurveyOpeningException;
import sth.exceptions.SurveyFinishingException;
import sth.exceptions.SurveyCancelingException;
import sth.exceptions.NoSuchSurveyException;


/**
 * Representation of Survey.
 */
public class Survey implements Serializable {
	
	/** List of answers */
	private List<Answer> _answers;
	/** Set of students who have answered the survey */
	private TreeSet<Integer> _studentIds;
	/** Survey's state */
	private SurveyState _state;

	public Survey() {
		_answers = new ArrayList<Answer>();
		_studentIds = new TreeSet<Integer>();
		_state = new CreatedSurveyState(this);
	}

	public String getStatus() {
		return _state.getStatus();
	}

	public void setState(SurveyState state) {
		_state = state;
	}

	public void cancel() throws SurveyCancelingException {
		_state.cancel();
	}

	public void close() throws SurveyClosingException {
		_state.close();
	}

	public void open() throws SurveyOpeningException {
		_state.open();
	}

	public void finalise() throws SurveyFinishingException {
		_state.finalise();
	}

	public void submitAnswer(int id, Answer answer) throws NoSuchSurveyException {
		_state.submit(id, answer);
		// Keep student from submiting twice
		if (hasAnswer(id))
			return;
		// Add student submission
		_studentIds.add(id);
		_answers.add(answer);
	}

	public boolean hasAnswer(int id){
		return _studentIds.contains(id);
	}

	public int getAvgHours(){
		int hours = 0;
		if (_answers.isEmpty())
			return 0;
		for(Answer answer : _answers)
			hours +=  answer.getHours();
		return hours / _answers.size();
	}

	public int getAnswerSize(){		
		return _answers.size();
	}

	public List<Answer> getResults() { 
		return _answers;
	}
}

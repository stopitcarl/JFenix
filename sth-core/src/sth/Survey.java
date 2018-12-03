package sth;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;

/**
 * Representation of Survey.
 */
public class Survey implements Serializable {
	
	/** List of answers */
	private List<Answer> _answers;
	/** Map of students */
	private Map<Integer, Student> _students;
	/** Survey's state */
	private SurveyState _state;

	public Survey() {
		_answers = new ArrayList<Answer>();
		_students = new TreeMap<Integer, Student>();
		_state = new CreatedSurveyState(this);
	}

	public void setState(SurveyState state) {
		_state = state;
	}

	public void cancel() {
		_state.cancel();
	}

	public void close() {
		_state.close();
	}

	public void open() {
		_state.open();
	}

	public void finalise() {
		_state.finalise();
	}

	public List<Answer> getResults() { 
		return _answers;
	}
}

package sth;

import java.util.List;
import java.io.Serializable;

/**
 * Representation of Survey.
 */
public class Survey implements Serializable {
	/** List of answers */
	private List<Answer> _answers;
	/** Project's state */
	private State _projectState;
	/** List of students */
	private List<Student> _students;
	/** Survey's state */
	private SurveyState _state = new CreatedSurveyState();

	public Survey() {
		// TODO: init vars
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
		return null;
	}
}

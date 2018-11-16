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
	private State _state;
	/** List of students */
	private List<Student> _students;

	public Survey() {
		// do nothing
	}

	public void cancel() {
		// do nothing
	}

	public void close() {
		// do nothing
	}

	public void open() {
		// do nothing
	}

	public void end() {
		// do nothing
	}

	public List<Answer> getResults() { 
		return null;
	}
}

package sth;

import java.util.List;
import java.io.Serializable;

public class Survey implements Serializable {
	private List<Answer> _answers;
	private State _state;
	private List<Student> _students;

	public Survey() {
		// TODO Auto-generated constructor
	}

	public void cancel() {

	}

	public void close() {

	}

	public void open() {

	}

	public void end() {

	}

	public List<Answer> getResults() {
		return null;
	}
}

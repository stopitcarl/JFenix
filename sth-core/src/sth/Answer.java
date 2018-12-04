package sth;
import java.io.Serializable;

public class Answer implements Serializable{ 
	private int _hours;
	private String _comment;
	
	public Answer(int hours, String comment) {
		_hours = hours;
		_comment = comment;
	}

	public int getHours() {
		return _hours;
	}

}

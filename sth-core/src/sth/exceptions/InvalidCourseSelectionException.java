package sth.exceptions;

/** Exception thrown when the requested person does not exist. */
public class InvalidCourseSelectionException extends Exception {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201809021324L;

	/** Course name. */
	private String _name;

	/**
	 * @param name
	 */
	public InvalidCourseSelectionException(String name) {
		_name = name;
	}

	/** @return id */
	public String getName() {
		return _name;
	}

}

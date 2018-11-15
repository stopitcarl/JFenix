package sth.exceptions;

/** Exception thrown when the requested subject does not exist. */
public class IllegalDisciplineException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201809021324L;

  /** Discipline name. */
  private String _name;

  /**
   * @param name
   */
  public IllegalDisciplineException(String name) {
    _name = name;
  }

  /** @return name */
  public String getName() {
    return _name;
  }
}
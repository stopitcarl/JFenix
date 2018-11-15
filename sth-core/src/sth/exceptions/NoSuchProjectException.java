package sth.exceptions;

/** Exception thrown when the requested project does not exist. */
public class NoSuchProjectException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201809021324L;

  /** Discipline name. */
  private String _name;

  /**
   * @param name
   */
  public NoSuchProjectException(String name) {
    _name = name;
  }

  /** @return name */
  public String getName() {
    return _name;
  }
}
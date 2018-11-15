package sth.exceptions;

/** Exception thrown when the requested project does not exist. */
public class NoSuchProjectNameException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201809021324L;

  /** Discipline name. */
  private String _projectName;
  private String _subjectName;

  /**
   * @param name
   */
  public NoSuchProjectNameException(String projectName, String subjectName) {
    _projectName = projectName;
    _subjectName = subjectName;
  }

  /** @return name */
  public String getProjectName() {
    return _projectName;
  }

  /** @return name */
  public String getSubjectName() {
    return _subjectName;
  }
}
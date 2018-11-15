package sth.exceptions;

/** Exception thrown when the requested project already exists. */
public class IllegalProjectNameException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201809021324L;

  /** Discipline name. */
  private String _projectName;
  private String _subjectName;

  /**
   * @param name
   */
  public IllegalProjectNameException(String projectName, String subjectName) {
    _projectName = projectName;
    _subjectName = subjectName;
  }

  /** @return project name */
  public String getProjectName() {
    return _projectName;
  }

  /** @return subject name */
  public String getSubjectName() {
    return _subjectName;
  }
}
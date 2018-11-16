package sth.exceptions;

/** Exception thrown when the requested project already exists. */
public class IllegalProjectNameException extends Exception {

  /** Class serial number */
  private static final long serialVersionUID = 201809021324L;

  /** Project name. */
  private String _projectName;
  /** Subject name. */
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
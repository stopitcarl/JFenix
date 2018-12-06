package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

import sth.exceptions.IllegalDisciplineException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.NoSuchSurveyException;
import sth.app.exceptions.NoSuchDisciplineException;
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSurveyException;

//FIXME import other classes if needed

/**
 * 4.3.5. Show survey results.
 */
public class DoShowSurveyResults extends Command<SchoolManager> {

  /** Name of project's discipline */
  Input<String> _disciplineName;
  /** Name of project to be created */
  Input<String> _projectName;

  /**
   * @param receiver
   */
  public DoShowSurveyResults(SchoolManager receiver) {
    super(Label.SHOW_SURVEY_RESULTS, receiver);
    _disciplineName = _form.addStringInput(Message.requestDisciplineName());
    _projectName = _form.addStringInput(Message.requestProjectName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      _display.popup(_receiver.showSurveyResults(_disciplineName.value(), _projectName.value()));
    } catch (IllegalDisciplineException e) {
      throw new NoSuchDisciplineException(e.getName());
    } catch (NoSuchProjectNameException e) {
      throw new NoSuchProjectException(e.getSubjectName(), e.getProjectName());
    } catch (NoSuchSurveyException e) {
      throw new NoSurveyException(_disciplineName.value(), _projectName.value());
    }
  }

}

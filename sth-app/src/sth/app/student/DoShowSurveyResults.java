package sth.app.student;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

import sth.exceptions.IllegalDisciplineException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.NoSuchSurveyException;
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSuchDisciplineException;
import sth.app.exceptions.NoSurveyException;
//FIXME import other classes if needed

/**
 * 4.4.3. Show survey results.
 */
public class DoShowSurveyResults extends Command<SchoolManager> {

  Input<String> _subject;
  Input<String> _project;

  /**
   * @param receiver
   */
  public DoShowSurveyResults(SchoolManager receiver) {
    super(Label.SHOW_SURVEY_RESULTS, receiver);
    _subject = _form.addStringInput(Message.requestDisciplineName());
    _project = _form.addStringInput(Message.requestProjectName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      _display.popup(_receiver.showSurveyResults(_subject.value(), _project.value()));
    } catch (IllegalDisciplineException e) {
      throw new NoSuchDisciplineException(e.getName());
    } catch (NoSuchProjectNameException e) {
      throw new NoSuchProjectException(e.getSubjectName(), e.getProjectName());
    } catch (NoSuchSurveyException e) {
      throw new NoSurveyException(_subject.value(), _project.value());
    }
  }

}

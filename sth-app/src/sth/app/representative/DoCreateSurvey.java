package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.app.exceptions.DuplicateSurveyException;
import sth.SchoolManager;
import sth.exceptions.TooManySurveysException;
import sth.exceptions.IllegalDisciplineException;
import sth.exceptions.NoSuchProjectNameException;
import sth.app.exceptions.NoSuchDisciplineException;
import sth.app.exceptions.NoSuchProjectException;

/**
 * 4.5.1. Create survey.
 */
public class DoCreateSurvey extends Command<SchoolManager> {
  
  Input<String> _subject;
  Input<String> _project;

  /**
   * @param receiver
   */
  public DoCreateSurvey(SchoolManager receiver) {
    super(Label.CREATE_SURVEY, receiver);
    _subject = _form.addStringInput(Message.requestDisciplineName());
    _project = _form.addStringInput(Message.requestProjectName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
        _receiver.createSurvey(_subject.value(), _project.value());    
    } catch (TooManySurveysException e){
      throw new DuplicateSurveyException(_subject.value(), _project.value());
    } catch(IllegalDisciplineException e){
			throw new NoSuchDisciplineException(e.getName());
	  } catch(NoSuchProjectNameException e){    
			throw new NoSuchProjectException(e.getSubjectName(), e.getProjectName());
		}

  }

}


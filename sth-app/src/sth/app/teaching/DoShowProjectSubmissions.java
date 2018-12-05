package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

import sth.exceptions.IllegalDisciplineException;
import sth.exceptions.NoSuchProjectNameException;
import sth.app.exceptions.NoSuchDisciplineException;
import sth.app.exceptions.NoSuchProjectException;

//FIXME import other classes if needed

/**
 * 4.3.3. Show project submissions.
 */
public class DoShowProjectSubmissions extends Command<SchoolManager> {

  /** Name of project's discipline */
  Input<String> _disciplineName;
  /** Name of project to be created */
	Input<String> _projectName;
	  

  /**
   * @param receiver
   */
  public DoShowProjectSubmissions(SchoolManager receiver)  {
    super(Label.SHOW_PROJECT_SUBMISSIONS, receiver);    
    _disciplineName = _form.addStringInput(Message.requestDisciplineName());
    _projectName = _form.addStringInput(Message.requestProjectName()); 
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
     	_display.popup(_receiver.showProjectSubmissions(_disciplineName.value(), _projectName.value()));
  	} catch(IllegalDisciplineException e){
		  throw new NoSuchDisciplineException(e.getName());
	  } catch(NoSuchProjectNameException e){    
			throw new NoSuchProjectException(e.getSubjectName(), e.getProjectName());
		}
  }
}

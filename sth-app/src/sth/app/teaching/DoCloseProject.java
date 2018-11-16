package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;
import sth.exceptions.IllegalDisciplineException;
import sth.exceptions.NoSuchProjectNameException;
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSuchDisciplineException;


/**
 * 4.3.2. Close project.
 */
public class DoCloseProject extends Command<SchoolManager> {

    /** Name of project to be closed */
    Input<String> _projectName;
    /** Name of project's subject. */
    Input<String> _disciplineName;

    /**
    * @param receiver
    */
    public DoCloseProject(SchoolManager receiver) {
      super(Label.CLOSE_PROJECT, receiver);
      _disciplineName = _form.addStringInput(Message.requestDisciplineName());
      _projectName = _form.addStringInput(Message.requestProjectName());    
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
      _form.parse();
        try{
          _receiver.closeProject(_disciplineName.value(), _projectName.value());
        } catch(IllegalDisciplineException e){
              throw new NoSuchDisciplineException(e.getName());
        } catch(NoSuchProjectNameException e){    
              throw new NoSuchProjectException(e.getSubjectName(), e.getProjectName());
      }
    }

  }

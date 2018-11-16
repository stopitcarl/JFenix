package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;
import sth.exceptions.IllegalDisciplineException;
import sth.app.exceptions.NoSuchDisciplineException;

/**
 * 4.3.4. Show discipline students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {

  /** Name of discipline */
  Input<String> _disciplineName;

  /**
   * @param receiver
   */
  public DoShowDisciplineStudents(SchoolManager receiver) {
    super(Label.SHOW_COURSE_STUDENTS, receiver);
    _disciplineName =  _form.addStringInput(Message.requestDisciplineName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
  try {
    _display.popup(_receiver.showDisciplineStudents(_disciplineName.value()));
  } catch (IllegalDisciplineException e) {
    throw new NoSuchDisciplineException(e.getName());    
  } 

  }

}

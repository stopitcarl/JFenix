package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;
import sth.exceptions.IllegalDisciplineException;
import sth.exceptions.IllegalProjectNameException;
import sth.app.exceptions.NoSuchDisciplineException;
import sth.app.exceptions.DuplicateProjectException;

//FIXME import other classes if needed

/**
 * 4.3.1. Create project.
 */
public class DoCreateProject extends Command<SchoolManager> {

	/** Name of project to be created */
	Input<String> _projectName;
	/** Name of project's discipline */
	Input<String> _disciplineName;

	/**
	 * @param receiver
	 */
	public DoCreateProject(SchoolManager receiver) {
		super(Label.CREATE_PROJECT, receiver);
		_disciplineName = _form.addStringInput(Message.requestDisciplineName());
		_projectName = _form.addStringInput(Message.requestProjectName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		try {
			_receiver.createProject(_disciplineName.value(), _projectName.value());
		} catch (IllegalDisciplineException e) {
			throw new NoSuchDisciplineException(e.getName());
		} catch (IllegalProjectNameException e) {
			throw new DuplicateProjectException(e.getSubjectName(), e.getProjectName());
		}

	}
}

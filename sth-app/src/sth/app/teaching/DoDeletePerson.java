package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;
import sth.exceptions.NoSuchPersonIdException;
import sth.app.exceptions.NoSuchPersonException;

//FIXME import other classes if needed

/**
 * 4.3.1. Create project.
 */
public class DoDeletePerson extends Command<SchoolManager> {

    /** Name of project to be created */
    Input<Integer> _personName;

    /**
     * @param receiver
     */
    public DoDeletePerson(SchoolManager receiver) {
        super(Label.DELETE_PERSON, receiver);
        _personName = _form.addIntegerInput(Message.requestPersonId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		try {
			_receiver.deletePerson(_personName.value());
		} catch (NoSuchPersonIdException e) {
			throw new NoSuchPersonException(e.getId());
	    }
    }
}

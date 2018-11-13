package sth.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<SchoolManager> {

	// FIXME add input fields if needed

	/**
	 * @param receiver
	 */
	public DoOpen(SchoolManager receiver) {
		super(Label.OPEN, receiver);
		// FIXME initialize input fields if needed
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		try {
			// FIXME implement command
			if (1 == 1)
				throw new FileNotFoundException();
			else
				throw new ClassNotFoundException();
		} catch (FileNotFoundException fnfe) {
			_display.popup(Message.fileNotFound());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}

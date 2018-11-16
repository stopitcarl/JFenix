package sth.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.exceptions.NoSuchPersonIdException;
import sth.app.exceptions.NoSuchPersonException;
import sth.SchoolManager;


/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<SchoolManager> {

	/** Name of file to be opened. */
	Input<String> _fileName;

	/**
	 * @param receiver
	 */
	public DoOpen(SchoolManager receiver) {
		super(Label.OPEN, receiver);
		_fileName = _form.addStringInput(Message.openFile());		
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		try {
			 _receiver.importSchoolFile(_fileName.value());
		} catch (FileNotFoundException fnfe) {
			_display.popup(Message.fileNotFound());
		} catch (NoSuchPersonIdException e) {
			throw new NoSuchPersonException(e.getId());
		}catch (ClassNotFoundException|IOException ex){
			ex.printStackTrace();
		}
	}

}

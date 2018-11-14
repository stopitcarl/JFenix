package sth.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.exceptions.ImportFileException;
import sth.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<SchoolManager> {

	// FIXME add input fields if needed
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
	public final void execute() {
		_form.parse();
		try {
			 _receiver.importNewFile(_fileName.value());
		} catch (FileNotFoundException fnfe) {
			_display.popup(Message.fileNotFound());
		} catch (ImportFileException e) {
			e.printStackTrace();
		}
	}

}

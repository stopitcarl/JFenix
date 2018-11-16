package sth.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> {
  /** Name of file to be saved */
  Input<String> _fileName;

  /**
   * @param receiver
   */
  public DoSave(SchoolManager receiver) {
    super(Label.SAVE, receiver);
    _fileName = _form.addStringInput(Message.newSaveAs());		
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    try{
    	if(_receiver.hasFileName())
        	_receiver.save();
      	else{
        	_form.parse();
        	_receiver.save(_fileName.value());      
      	}
    } catch (FileNotFoundException ex){
    	_display.popup("file could not be found");
    } catch (IOException es){
      	es.printStackTrace();
    } catch (Exception ops){
    	_display.popup("except ion");
    }
  }


}

package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.2.2. Change phone number.
 */
public class DoChangePhoneNumber extends Command<SchoolManager> {
  
  /** New phone number to change to. */
  Input<String> _phoneNum;

  /**
   * @param receiver
   */
  public DoChangePhoneNumber(SchoolManager receiver) {
    super(Label.CHANGE_PHONE_NUMBER, receiver);    
    _phoneNum = _form.addStringInput(Message.requestPhoneNumber());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    _receiver.changePhoneNumber(_phoneNum.value());
    _display.popup(_receiver.showPerson());
  }

}

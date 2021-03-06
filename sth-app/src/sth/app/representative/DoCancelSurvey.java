package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;
import sth.exceptions.TooManySurveysException;
import sth.exceptions.IllegalDisciplineException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.NoSuchSurveyException;
import sth.exceptions.SurveyNotEmptyException;
import sth.exceptions.SurveyFinishingException;
import sth.exceptions.SurveyCancelingException;
import sth.app.exceptions.NoSuchDisciplineException;
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSurveyException;
import sth.app.exceptions.NonEmptySurveyException;
import sth.app.exceptions.SurveyFinishedException;

//FIXME import other classes if needed

/**
 * 4.5.2. Cancel survey.
 */
public class DoCancelSurvey extends Command<SchoolManager> {

  	Input<String> _subject;
  	Input<String> _project;

  	/**
   	* @param receiver
   	*/
	public DoCancelSurvey(SchoolManager receiver) {
		super(Label.CANCEL_SURVEY, receiver);
		_subject = _form.addStringInput(Message.requestDisciplineName());
		_project = _form.addStringInput(Message.requestProjectName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
    	try{
    	 	_receiver.cancelSurvey(_subject.value(), _project.value());                    	  
    	} catch(IllegalDisciplineException e){
			throw new NoSuchDisciplineException(e.getName());
		} catch(NoSuchProjectNameException e){    
			throw new NoSuchProjectException(e.getSubjectName(), e.getProjectName());
    	} catch(NoSuchSurveyException e){    
    		throw new NoSurveyException(_subject.value(), _project.value());
  		} catch(SurveyNotEmptyException e){    
	    	throw new NonEmptySurveyException(_subject.value(), _project.value());
	  	} catch(SurveyFinishingException e){    
	    	throw new SurveyFinishedException(_subject.value(), _project.value());
	  	} catch(SurveyCancelingException e){    
			e.printStackTrace();
		}
	}
}

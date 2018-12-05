package sth;

import java.io.Serializable;
import sth.exceptions.SurveyNotEmptyException;


public class OpenSurveyState extends SurveyState implements Serializable {
    public OpenSurveyState(Survey survey) {
        super(survey);
    }

    public String getStatus() {
        return "(aberto)";
    }

    @Override    
    public void submit(int id, Answer answer){
        // Do nothing
	}
    
    @Override
    public void close() {
        getSurvey().setState(new ClosedSurveyState(getSurvey()));
    }

    @Override
    public void cancel() throws SurveyNotEmptyException { 
        if (_survey.getResults().isEmpty())
            throw new SurveyNotEmptyException();
    }
}
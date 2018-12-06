package sth;

import java.io.Serializable;
import sth.exceptions.SurveyFinishingException;


public class FinalisedSurveyState extends SurveyState implements Serializable {
    public FinalisedSurveyState(Survey survey) {
        super(survey);
    }

    @Override
    public void finalise() {
        //Do nothing.
    }

    public String getStatus(SurveyShower shower){        
        return shower.showFinalised(this);
    }
    
    public boolean cancel() throws SurveyFinishingException { 
        throw new SurveyFinishingException();
    }
}
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

    public String getStatus(){
        Survey survey = getSurvey();
        String status = "- " + survey.getAnswerSize() + " respostas - "; // 20 respostas - 16 horas
        status += survey.getAvgHours() + " horas";
        return status;
    }
    
    public void cancel() throws SurveyFinishingException { 
        throw new SurveyFinishingException();
    }
}
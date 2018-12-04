package sth;

import java.io.Serializable;


public class CreatedSurveyState extends SurveyState {
    public CreatedSurveyState(Survey survey) {
        super(survey);
    }

    @Override
    public void open() {
        getSurvey().setState(new OpenSurveyState(getSurvey()));
    }

    public String getStatus(){
        return "(por abrir)";
    }
    
    public void cancel() {
        // do nothing
    }
}
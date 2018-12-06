package sth;

import java.io.Serializable;


public class CreatedSurveyState extends SurveyState implements Serializable {
    public CreatedSurveyState(Survey survey) {
        super(survey);
    }

    @Override
    public void open() {
        getSurvey().setState(new OpenSurveyState(getSurvey()));
    }

    public String getStatus(SurveyShower shower){
        return shower.showCreated(this);
    }
    
    public boolean cancel() {
        return true;
    }
}
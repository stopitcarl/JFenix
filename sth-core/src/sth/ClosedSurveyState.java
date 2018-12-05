package sth;

import java.io.Serializable;


public class ClosedSurveyState extends SurveyState implements Serializable {
    
    public ClosedSurveyState(Survey survey) {
        super(survey);
    }

    public String getStatus() {
        return "(fechado)";
    }

    @Override
    public void open() {
        getSurvey().setState(new OpenSurveyState(getSurvey()));
    }

    @Override
    public void close() {
        //Do nothing.
    }

    @Override
    public void finalise() {
        getSurvey().setState(new FinalisedSurveyState(getSurvey()));
    }

    @Override
    public void cancel() {
        getSurvey().setState(new OpenSurveyState(getSurvey()));
    }
}
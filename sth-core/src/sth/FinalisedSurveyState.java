package sth;

import java.io.Serializable;


public class FinalisedSurveyState extends SurveyState {
    public FinalisedSurveyState(Survey survey) {
        super(survey);
    }

    public void open() {
        //Erro. 
    }

    public void close() {
        //Erro.
    }

    public void finalise() {
        //Do nothing.
    }

    public void cancel() { // TODO: throw SurveyFinishedException
        //Erro.
    }
}
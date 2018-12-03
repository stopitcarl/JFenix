package sth;

import java.io.Serializable;


public class OpenSurveyState extends SurveyState {
    public OpenSurveyState(Survey survey) {
        super(survey);
    }

    public void open() {
        //Do nothing. --> acho que é suposto não fazer nada 
    }

    public void close() {
        getSurvey().setState(new ClosedSurveyState(getSurvey()));
    }

    public void finalise() {
        //Erro?
    }

    public void cancel() { // TODO: throw NonEmptySurveyException 
        //Apaga o survey
    }
}
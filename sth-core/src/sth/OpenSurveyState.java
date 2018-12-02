public class OpenSurveyState extends SurveyState {
    public OpenSurveyState(Survey survey) {
        super(survey);
    }

    public void open() {
        //Do nothing. --> acho que é suposto não fazer nada 
    }

    public void close() {
        getSurvey().setState(new CloseSurveyState(getSurvey()));
    }

    public void finalise() {
        //Erro.
    }

    public void cancel() {
        //Apaga o survey.
    }
}
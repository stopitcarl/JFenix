public class CreatedSurveyState extends SurveyState {
    public CreatedSurveyState(Survey survey) {
        super(survey);
    }

    public void open() {
        getSurvey().setState(new OpenSurveyState(getSurvey()));
    }

    public void close() {
        //Erro?
    }

    public void finalise() {
        //Erro?
    }

    public void cancel() {
        //Apaga o survey.
    }
}
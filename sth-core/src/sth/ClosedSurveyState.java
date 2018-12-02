public class ClosedSurveyState extends SurveyState {
    public ClosedSurveyState(Survey survey) {
        super(survey);
    }

    public void open() {
        getSurvey().setState(new OpenSurveyState(getSurvey())); 
    }

    public void close() {
        //Do nothing.
    }

    public void finalise() {
        getSurvey().setState(new FinalisedSurveyState(getSurvey()));
    }

    public void cancel() {
        getSurvey().setState(new OpenSurveyState(getSurvey()));
    }
}
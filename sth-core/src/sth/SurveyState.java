public abstract class SurveyState {
    private Survey _survey;

    public SurveyState(Survey survey) {
        _survey = survey;
    }

    public Survey getSurvey() {
        return _survey;
    }

    public abstract void open();
    public abstract void close();
    public abstract void finalise();
    public abstract void cancel(); 
}
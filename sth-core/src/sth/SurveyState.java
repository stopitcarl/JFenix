package sth;

import java.io.Serializable;
import sth.exceptions.SurveyClosingException;
import sth.exceptions.SurveyFinishingException;
import sth.exceptions.SurveyOpeningException;
import sth.exceptions.SurveyCancelingException;
import sth.exceptions.NoSuchSurveyException;

public abstract class SurveyState implements Serializable {
    protected Survey _survey;

    public SurveyState(Survey survey) {
        _survey = survey;
    }

    public Survey getSurvey() {
        return _survey;
    }

    public abstract String getStatus(SurveyShower shower);

    public void submit(int id, Answer answer) throws NoSuchSurveyException {
        throw new NoSuchSurveyException();
    }

    public void open() throws SurveyOpeningException {
        throw new SurveyOpeningException();
    }

    public void close() throws SurveyClosingException {
        throw new SurveyClosingException();
    }

    public void finalise() throws SurveyFinishingException {
        throw new SurveyFinishingException();
    }

    public abstract boolean cancel() throws SurveyCancelingException;
}
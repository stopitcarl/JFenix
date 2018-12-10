package sth.surveys;

import java.io.Serializable;

import sth.Answer;

import sth.exceptions.SurveyNotEmptyException;

public class OpenSurveyState extends SurveyState implements Serializable {
    
    public OpenSurveyState(Survey survey) {
        super(survey);
        survey.notifyObservers("Pode preencher inquérito do projecto " +  survey.getProjectName() 
         + " da disciplina " + survey.getSubjectName());
    }

    public String getStatus(SurveyShower shower) {
        return shower.showOpen(this);
    }

    @Override
    public void submit(int id, Answer answer) {
        // Check if id is in answer
        Survey survey = getSurvey();
        // If student hasn't answered, add submission
        if (!survey.hasAnswer(id)) {
            survey.getStudents().add(id);
            survey.getResults().add(answer);
        }
    }

    @Override
    public void close() {
        getSurvey().setState(new ClosedSurveyState(getSurvey()));
    }

    @Override
    public boolean cancel() throws SurveyNotEmptyException {
        if (!_survey.getResults().isEmpty())
            throw new SurveyNotEmptyException();
        return true;
    }
}
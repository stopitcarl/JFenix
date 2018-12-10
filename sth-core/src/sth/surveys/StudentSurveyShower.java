package sth.surveys;

import sth.Student;

public class StudentSurveyShower extends SurveyShower {

    @Override
    public String showFinalised(FinalisedSurveyState s) {
        Survey survey = s.getSurvey();
        String status = "\n * Número de respostas: " + survey.getAnswerSize() + "\n";
        status += " * Tempo médio (horas): " + survey.getAvgHours();
        return status;
    }
}
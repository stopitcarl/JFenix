package sth;

public class StudentSurveyShower extends SurveyShower {
    private Student _student;

    public StudentSurveyShower(Student s) {
        _student = s;
    }

    @Override
    public String showFinalised(SurveyState s) {
        Survey survey = s.getSurvey();
        String status = "* Número de repostas: " + survey.getAnswerSize() + "\n";
        status += "* Tempo médio (horas): " + survey.getAvgHours();
        return status;
    }
}
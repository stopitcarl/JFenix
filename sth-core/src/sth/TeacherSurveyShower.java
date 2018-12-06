package sth;

public class TeacherSurveyShower extends SurveyShower {

    @Override
    public String showFinalised(SurveyState s) {
        Survey survey = s.getSurvey();

        String status = "* Número de submissões: " + getSubmissionSize() + "\n";
        status += "* Número de repostas: " + survey.getAnswerSize() + "\n";
        status += "* Tempos de resolução (horas) (mínimo, médio, máximo): " + survey.getMinHours() + ", " 
                + survey.getAvgHours() + ", " + survey.getMaxHours();
        return status;
    }

    private int getSubmissionSize() {
        return getProject().getSubmissionSize();
    }
}

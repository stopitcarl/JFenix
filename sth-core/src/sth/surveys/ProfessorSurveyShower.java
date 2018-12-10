package sth.surveys;


public class ProfessorSurveyShower extends SurveyShower {

    @Override
    public String showFinalised(FinalisedSurveyState s) {
        Survey survey = s.getSurvey();

        String status = "\n * Número de submissões: " + getSubmissionSize() + "\n";
        status += " * Número de respostas: " + survey.getAnswerSize() + "\n";
        status += " * Tempos de resolução (horas) (mínimo, médio, máximo): " + survey.getMinHours() + ", " 
                + survey.getAvgHours() + ", " + survey.getMaxHours();
        return status;
    }

    private int getSubmissionSize() {
        return getProject().getSubmissionSize();
    }
}

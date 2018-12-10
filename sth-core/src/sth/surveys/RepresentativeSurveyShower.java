package sth.surveys;


public class RepresentativeSurveyShower extends SurveyShower {

    @Override
    public String showFinalised(FinalisedSurveyState s) {
        Survey survey = s.getSurvey();
        String status = "- " + survey.getAnswerSize() + " respostas - ";
        status += survey.getAvgHours() + " horas";
        return status;
    }

}
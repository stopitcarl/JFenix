package sth.surveys;

import sth.Project;

public abstract class SurveyShower {
    private Project _project;

    public String showOpen(OpenSurveyState s) {
        return " (aberto)";
    }

    public String showClosed(ClosedSurveyState s) {
        return " (fechado)";
    }

    public String showCreated(CreatedSurveyState s) {
        return " (por abrir)";
    }

    public abstract String showFinalised(FinalisedSurveyState s);

    public Project getProject() {
        return _project;
    }

    public void setProject(Project project){
        _project = project;
    }
}
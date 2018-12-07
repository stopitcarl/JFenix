package sth;

public abstract class SurveyShower {
    private Project _project;

    public String showOpen(SurveyState s) {
        return " (aberto)";
    }

    public String showClosed(SurveyState s) {
        return " (fechado)";
    }

    public String showCreated(SurveyState s) {
        return " (por abrir)";
    }

    public abstract String showFinalised(SurveyState s);

    public Project getProject() {
        return _project;
    }

    public void setProject(Project project){
        _project = project;
    }
}
package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;
import sth.exceptions.TooManySurveysException;
import sth.exceptions.IllegalDisciplineException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.NoSuchSurveyException;
import sth.exceptions.SurveyOpeningException;
import sth.app.exceptions.NoSuchDisciplineException;
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSurveyException;
import sth.app.exceptions.OpeningSurveyException;

/**
 * 4.5.3. Open survey.
 */
public class DoShowBiggestDiscipline extends Command<SchoolManager> {

    /**
     * @param receiver
     */
    public DoShowBiggestDiscipline(SchoolManager receiver) {
        super(Label.SHOW_BIGGEST_DISCIPLINE, receiver);
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _display.popup(_receiver.showBiggestDiscipline());
    }
}

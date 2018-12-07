package sth;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;
import java.io.Serializable;

import sth.exceptions.SurveyClosingException;
import sth.exceptions.SurveyOpeningException;
import sth.exceptions.SurveyFinishingException;
import sth.exceptions.SurveyCancelingException;
import sth.exceptions.NoSuchSurveyException;

/**
 * Representation of Survey.
 */
public class Survey implements Observable, Serializable {

	/** List of answers */
	private List<Answer> _answers;
	/** Set of students who have answered the survey */
	private TreeSet<Integer> _studentIds;
	/** Survey's state */
	private SurveyState _state;
	/** Observer to notofy changes */
	private Observer _observer;
	/** Project */
	Project _project;
	/** Name of subject */
	String _subjectName;

	public Survey(Subject s, Project project) {
		_answers = new ArrayList<Answer>();
		_studentIds = new TreeSet<Integer>();
		_state = new CreatedSurveyState(this);
		_project = project;
		_subjectName = s.getName();
		registerObserver(s);
	}

	public String getStatus(SurveyShower shower) {
		return _state.getStatus(shower);
	}

	public String getProjectName() {
		return _project.getName();
	}

	public String getSubjectName() {
		return _subjectName;
	}

	public void registerObserver(Observer o) {
		_observer = o;
	}

	public void removeObserver(Observer o) {
		_observer = null;
	}

	public void notifyObservers(String notification) {
		_observer.update(notification);
	}

	public int getMinHours() {
		if (_answers.isEmpty())
			return 0;
		int min = _answers.get(0).getHours();
		for (Answer ans : _answers)
			if (ans.getHours() < min)
				min = ans.getHours();
		return min;
	}

	public int getMaxHours() {
		if (_answers.isEmpty())
			return 0;
		int max = _answers.get(0).getHours();
		for (Answer ans : _answers)
			if (ans.getHours() > max)
				max = ans.getHours();
		return max;
	}

	public int getAvgHours() {
		int hours = 0;
		if (_answers.isEmpty())
			return 0;
		for (Answer answer : _answers)
			hours += answer.getHours();
		return hours / _answers.size();
	}

	public int getAnswerSize() {
		return _answers.size();
	}

	public List<Answer> getResults() {
		return _answers;
	}

	public void setState(SurveyState state) {
		_state = state;
	}

	public boolean cancel() throws SurveyCancelingException {
		return _state.cancel();
	}

	public void close() throws SurveyClosingException {
		_state.close();
	}

	public void open() throws SurveyOpeningException {
		_state.open();
	}

	public void finalise() throws SurveyFinishingException {
		_state.finalise();
	}

	public void submitAnswer(int id, Answer answer) throws NoSuchSurveyException {
		_state.submit(id, answer);
	}

	public Set<Integer> getStudents() {
		return _studentIds;
	}

	public boolean hasAnswer(int id) {
		return _studentIds.contains(id);
	}

}

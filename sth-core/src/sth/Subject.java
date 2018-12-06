package sth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Set;

import sth.exceptions.IllegalProjectNameException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.SurveyOpeningException;

/**
 * Representation of Subject
 */
public class Subject implements Serializable, Observer, Observable {
	/** Subject's name */
	private String _name;
	/** Map of project name and projects */
	private Map<String, Project> _projects;
	/** list of subscriptions */
	private Set<Observer> _subscriptions;

	/**
	 * @param name Subject's name
	 */
	public Subject(String name) {
		_name = name;
		_projects = new TreeMap<String, Project>();
		_subscriptions = new TreeSet<Observer>();
	}

	/**
	 * 
	 * @return subject's name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @return List of projects
	 */
	public List<Project> getProjects() {
		return new ArrayList<Project>(_projects.values());
	}

	/**
	 * @return List of projects
	 */
	public Project getProject(String projectName) {
		return _projects.get(projectName);
	}

	/**
	 * adds project with projectName to the list of projects
	 * 
	 * @param projectName
	 * @throws IllegalProjectNameException
	 */
	public void addProject(String projectName) throws IllegalProjectNameException {
		if (_projects.get(projectName) != null)
			throw new IllegalProjectNameException(projectName, _name);
		else
			_projects.put(projectName, new Project(projectName));
	}

	/**
	 * closes project with projectName
	 * 
	 * @param projectName
	 * @throws NoSuchProjectNameException
	 */
	public void closeProject(String projectName) throws NoSuchProjectNameException, SurveyOpeningException {
		Project project = _projects.get(projectName);

		if (project != null) {
			project.closeProject();
			return;
		} else
			throw new NoSuchProjectNameException(projectName, _name);
	}

	public void registerObserver(Observer o) {
		_subscriptions.add(o);
	}

	public void removeObserver(Observer o) {
		_subscriptions.add(o);
	}

	public void update(String notification) {
		notifyObservers(notification);
	}

	public void notifyObservers(String notification) {
		for (Observer subsriber : _subscriptions)
			subsriber.update(notification);
	}

	public boolean equals(Object o) {
		if (o instanceof Subject) {
			Subject s = (Subject) o;
			return _name.equals(s.getName());
		}
		return false;
	}
}

package sth;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import sth.exceptions.IllegalProjectNameException;
import sth.exceptions.NoSuchProjectNameException;

/**
 * Representation of Subject
 */
public class Subject implements Serializable{
	/** Subject's name */
	private String _name;
	/** List of projects */
	private List<Project> _projects;
	/** list of subscriptions */ 
	private List<Person> _subscriptions;

	/**
 	* @param name Subject's name
 	*/
	public Subject(String name) {
		_name = name;
		_projects = new LinkedList<Project>();
		_subscriptions = new LinkedList<Person>();
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
		return _projects;		
	}
	
	/**
	 * adds project with projectName to the list of projects
	 * @param projectName
	 * @throws IllegalProjectNameException
	 */
	 public void addProject(String projectName) throws IllegalProjectNameException {
		for (Project p : _projects)
			if(projectName.equals(p.getName()))
				throw new IllegalProjectNameException(projectName, _name);
		 _projects.add(new Project(projectName)); 
	}
	 
	/**
	 * closes project with projectName
	 * @param projectName
	 * @throws NoSuchProjectNameException
	 */
	 public void closeProject(String projectName) throws NoSuchProjectNameException { 
		for (Project p : _projects)
			if(projectName.equals(p.getName())){
				p.closeProject();
				return;
			}
		throw new NoSuchProjectNameException(projectName, _name);
	}
	
	/*
	public void subribe(Person p) {

	}

	public void unsubscribe(Person p) {

	}
	*/

	public boolean equals(Object o) {
		if (o instanceof Subject) {
			Subject s = (Subject) o;
			return _name.equals(s.getName());
		}
		return false;
	}
}

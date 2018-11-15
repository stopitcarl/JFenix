package sth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import sth.exceptions.IllegalProjectNameException;
import sth.exceptions.NoSuchProjectException;

public class Subject implements Serializable{
	private String _name;
	private List<Project> _projects;
	private List<Person> _subscriptions;

	public Subject(String projectName) {
		_name = projectName;
		_projects = new ArrayList<Project>();
		_subscriptions = new ArrayList<Person>();
	}

	public String getName() {
		return _name;
	}

	public List<Project> getProjects() {
		return _projects;		
	}
	
	 public void addProject(String projectName) throws IllegalProjectNameException {
		for (Project p : _projects)
			if(projectName == p.getName())
				throw new IllegalProjectNameException(projectName, _name);
		 _projects.add(new Project(projectName)); 
	}
	 
	 public void closeProject(String projectName) throws NoSuchProjectException { 
		for (Project p : _projects)
			if(projectName == p.getName())
				p.closeProject();
		throw new NoSuchProjectException(projectName);
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
			return _name == s.getName();
		}
		return false;
	}
}

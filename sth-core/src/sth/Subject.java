package sth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import sth.exceptions.IllegalProjectNameException;
import sth.exceptions.NoSuchProjectNameException;

public class Subject implements Serializable{
	private String _name;
	private List<Project> _projects;
	private List<Person> _subscriptions;

	public Subject(String name) {
		_name = name;
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
			if(projectName.equals(p.getName()))
				throw new IllegalProjectNameException(projectName, _name);
		 _projects.add(new Project(projectName)); 
	}
	 
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

package sth;

import java.util.List;

public class Subject {
	private String _name;
	// private List<Project> _projects;
	private List<Person> _subscriptions;

	public Subject(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	/*
	 * public void addProject(String name) { // TODO: add DuplicateProjectException
	 * (nÃ£o sei se Ã© aqui) _projects.add(new Project(name)); }
	 * 
	 * public void closeProject(String name) { // TODO: add NoSuchProjectException
	 * (nÃ£o sei se Ã© aqui) for (Project p : _projects) if
	 * (name.equals(p.getName())) p.closeProject(); }
	 * 
	 */
	public void subribe(Person p) {

	}

	public void unsubscribe(Person p) {

	}
}

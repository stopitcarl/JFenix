package sth;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Locale;
import java.text.Collator;
import java.util.Collections;

public class Professor extends Person {	
	TreeMap<Course ,ArrayList<Subject>> _taughtSubjects;


	public Professor(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);		
		_taughtSubjects = new TreeMap<Course ,ArrayList<Subject>>();
	}

	public void addSubject(Course course, Subject subject) {		
		if (!_taughtSubjects.containsKey(course))
			_taughtSubjects.put(course, new ArrayList<Subject>());		
		
		_taughtSubjects.get(course).add(subject);
	}

/*
* Informática - Análise e Síntese de Algoritmos
* Informática - Inteligência Artificial
* Informática - Sistemas Operativos
* Informática - Fundamentos da Programação
* Informática - Programação com Objectos
*/
	 
	public List<String> getClasses(){ // TODO: find a better way to do this
		ArrayList<String> classes = new ArrayList<String>();

		for (Course course : _taughtSubjects.keySet())
			for(Subject subject : _taughtSubjects.get(course))
				classes.add("* " + course.getName() + " - " + subject.getName());
		
		Collections.sort(classes, Collator.getInstance(Locale.getDefault()));
		return classes;
	}

	@Override 
	public String toString() {
		return "DOCENTE|" + super.toString();
	}

	/*
	 * public void createProject(Subject s, String name) { s.addProject(name); }
	 * 
	 * public void closeProject(Subject s, String name) { s.closeProject(name); }
	 */
}

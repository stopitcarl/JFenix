package sth;

public interface PersonVisitor{
    public String showPerson(Student s);
    public String showPerson(Professor s);
    public String showPerson(Administrative s);
}
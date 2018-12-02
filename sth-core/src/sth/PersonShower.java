package sth;


public class PersonShower implements Visitor {

    public String showPerson(Student s){
        String info = s.toString();
        for(String classes : s.getClasses())
            info +=  "\n" + classes;
        return info + "\n";        
    }

    public String showPerson(Professor p){
        String info = p.toString(); 
        for(String classes : p.getClasses())
            info +=  "\n" + classes;
        return info + "\n";        

    }

    public String showPerson(Administrative a){       
        return a.toString() + "\n";        
    }
}
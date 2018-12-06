package sth;

import java.util.List;
import java.text.Collator;
import java.util.Collections;
import java.util.Locale;

public class PersonShower implements PersonVisitor {

    public String showPerson(Student s) {
        String info = s.toString();

        List<String> subjects = s.getClasses();
        sort(subjects);
        for (String subject : subjects)
            info += "\n" + subject;
        return info + "\n";
    }

    public String showPerson(Professor p) {
        String info = p.toString();

        List<String> subjects = p.getClasses();
        sort(subjects);
        for (String subject : subjects)
            info += "\n" + subject;
        return info + "\n";
    }

    public String showPerson(Administrative a) {
        return a.toString() + "\n";
    }

    private void sort(List<String> list) {
        Collections.sort(list, Collator.getInstance(Locale.getDefault()));
    }
}
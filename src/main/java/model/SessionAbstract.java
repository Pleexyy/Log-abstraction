package model;

import java.util.ArrayList;
import java.util.List;

public class SessionAbstract {

    private List<Conversation> refs = new ArrayList<>();
    private List<String> typage = new ArrayList<>();

    public SessionAbstract(Conversation conv,List<String> typage){
        refs.add(conv);
        typage.addAll(typage);
    }

    public List<String> getTypage() {
        return typage;
    }

    public List<Conversation> getRefs() {
        return refs;
    }
}

package model;

import java.util.ArrayList;
import java.util.List;

public class SessionAbstract {

    private final List<Conversation> refs = new ArrayList<>();
    private final List<String> typage = new ArrayList<>();

    public SessionAbstract(Conversation conv, List<String> typage) {
        refs.add(conv);
        this.typage.addAll(typage);
    }

    /**
     * @param conv de type Conversation
     */
    public void addRef(Conversation conv) {
        refs.add(conv);
    }

    /**
     * retourne les typages
     *
     * @return la liste typage de type String
     */
    public List<String> getTypage() {
        return typage;
    }

    /**
     * retourne les références
     *
     * @return la liste refs de type Conversation
     */
    public List<Conversation> getRefs() {
        return refs;
    }

}

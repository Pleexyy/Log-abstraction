package model;

import java.util.ArrayList;
import java.util.List;

public class AbstacteurSession {

    /**
     * retourne une liste de sessions abstraites
     *
     * @param convSet de type ConversationSet
     * @return sessionAbstracts
     */
    public List<SessionAbstract> abstracteur(ConversationSet convSet) {

        List<SessionAbstract> sessionAbstracts = new ArrayList<>();

        for (Conversation conv : convSet.getConversationSet()) {
            List<String> typeString = new ArrayList<>();
            conv.getConv().forEach(e -> typeString.add(e.getLabel() + "(" + e.getEventWithTypes() + ")"));
            ajouterDansSessionAbstract(sessionAbstracts, conv, typeString);
        }
        return sessionAbstracts;
    }

    /**
     * @param sessionAbstracts liste de type SessionAbstract
     * @param conv             de type Conversation
     * @param type             liste de type String
     */
    private void ajouterDansSessionAbstract(List<SessionAbstract> sessionAbstracts, Conversation conv, List<String> type) {
        for (SessionAbstract s : sessionAbstracts) {
            if (type.equals(s.getTypage())) {
                s.addRef(conv);
                return;
            }
        }
        sessionAbstracts.add(new SessionAbstract(conv, type));
    }

}

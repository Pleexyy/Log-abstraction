package model;

public class AbstacteurSession {

    public AbstacteurSession() {

    }

    public abstracteur(ConversationSet convSet) {
        for (Conversation conv : convSet.getConversationSet()) {
            for (int i = 0; i < conv.size(); i++) {
                String type = conv.getConv().get(i).getEventWithTypes();
                StringBuilder typeString = new StringBuilder();
                typeString.append(type);
            }
        }
    }

}

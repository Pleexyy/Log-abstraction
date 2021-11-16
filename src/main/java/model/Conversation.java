package model;

import java.util.ArrayList;
/*
 * represent a conversation
 * @param conv save the conversation
 */

public class Conversation {
    private final ArrayList<Event> conv;

    public Conversation() {
        conv = new ArrayList<>();
    }

    /**
     * retourne la taille de la conversation
     *
     * @return la taille de la liste des conversations de type Event
     */
    public int size() {
        return this.conv.size();
    }

    public void nouvelEvent(Event evenement) {
        this.conv.add(evenement);
    }

    /**
     * retourne les conversations
     *
     * @return la liste des conversations de type Event
     */
    public ArrayList<Event> getConv() {
        return conv;
    }
}

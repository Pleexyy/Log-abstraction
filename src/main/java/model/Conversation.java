package model;

import java.util.ArrayList;
/*
 * represent a conversation
 * @param  conv  save the conversation
 * @param  choosedKeys  save the choosed keys for the conversation
 * @param  assignments  save the assignments used for all keys
 * @param  cle  save the last used keys
 */

public class Conversation {
    public ArrayList<Event> conv;

    public ArrayList<ArrayList<String>> choosedKeys;

    public ArrayList<String> assignments;
    public ArrayList<String> cle;

    public Conversation() {
        conv = new ArrayList<>();
        choosedKeys = new ArrayList<>();
        assignments = new ArrayList<>();
        cle = new ArrayList<>();
    }

    public Conversation(Conversation conversation) {
        conv = new ArrayList<>();
        choosedKeys = new ArrayList<>();
        assignments = new ArrayList<>();
        cle = new ArrayList<>();

        conv.addAll(conversation.conv);
        choosedKeys.addAll(conversation.choosedKeys);
        assignments.addAll(conversation.assignments);
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

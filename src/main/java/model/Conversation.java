package model;

import java.util.ArrayList;
/*
 * represent a conversation
 * @param  conv  save the conversation
 * @param  choosedKeys  save the choosed keys for the conversation
 * @param  assignments  save the assignments used for all keys
 * @param  cle  save the last used keys
 * @param  nbrequest  number of requests in the conversation
 * @param  nbreponse  number of responses in the conversation
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
        conv.addAll(conversation.conv);

        choosedKeys = new ArrayList<>();
        choosedKeys.addAll(conversation.choosedKeys);

        assignments = new ArrayList<>();

        assignments.addAll(conversation.assignments);

        cle = new ArrayList<>();
    }

    //renvoie la taille de la conversation
    public int size() {
        return this.conv.size();
    }

    public void nouvelEvent(Event evenement) {
        this.conv.add(evenement);
    }

    //renvoie la conversation
    public ArrayList<Event> getConv() {
        //Collections.unmodifiableList(conv);
        return conv;
    }
}

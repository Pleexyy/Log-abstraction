package model;

import java.util.ArrayList;

/*
 * Represent a ConversationSet
 *
 * @param  conversation  list of conversation
 */

public class ConversationSet {
    private final ArrayList<Conversation> conversations;

    public ConversationSet() {
        conversations = new ArrayList<>();
    }

    public void addConversation(Conversation c) {
        conversations.add(c);
    }

    /**
     * retourne la taille des conversations
     *
     * @return la taille de la liste des conversations de type Conversation
     */
    public int getSize() {
        return this.conversations.size();
    }

    /**
     * retourne les conversations
     *
     * @return la liste des conversations de type Conversation
     */
    public ArrayList<Conversation> getConversationSet() {
        return this.conversations;
    }

}
package model;

import java.util.ArrayList;

/*
* Represent a ConversationSet
*
* @param  ConvSet  list of conversation
*/

public class ConversationSet{
	public ArrayList<Conversation> conversations;

	public ConversationSet() {
		conversations =new ArrayList<Conversation>();
	}

	public void addConversation(Conversation c){
		conversations.add(c);
	}

	public int getSize() {
		return this.conversations.size();
	}

	public ArrayList<Conversation> getConversationSet() {
		return this.conversations;
	}
	
}
package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
	
	public ConversationSet(ConversationSet Set) {
		conversations =new ArrayList<Conversation>();
		for(Conversation conv : Set.conversations) {
			conversations.add(new Conversation(conv));
		}
		
	}
	
	public ConversationSet(ConversationSet Set,ArrayList<String> key, Event event) {
		conversations =new ArrayList<Conversation>();
		for(Conversation conv : Set.conversations) {
			conversations.add(new Conversation(conv));
		}
		
	}


	public ConversationSet(ConversationSet Set, Conversation conv, Conversation conv2, ArrayList<String> nouvellecle,Event reqorrep) {

		conversations = new ArrayList<Conversation>();
		for (Conversation conver : Set.conversations) {
			if (!conver.getConv().equals(conv.getConv())) {
				conversations.add(new Conversation(conver));
			} else {
				conversations.add(new Conversation(conv2));
			}
		}


	}

	public ConversationSet(Conversation Conversation) {
		setConversationSet(new ArrayList<Conversation>());
		getConversationSet().add( Conversation);
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
	
	public void setConversationSet (ArrayList<Conversation> ensembleconv) {
		this.conversations = ensembleconv;
	}
	
	public static void setEnsembleConversation(Conversation Conversation, ConversationSet theSet){
		theSet.getConversationSet().add(Conversation);
		
	}
	
	public ArrayList<ArrayList<Event>> getTrace(){
		ArrayList<ArrayList<Event>> renvoi = new ArrayList<ArrayList<Event>>();
		for (Conversation conv : this.conversations) {
			ArrayList<Event> rajout=new ArrayList<Event>();
			for (Event event : conv.getConv()) {
				rajout.add(event);
			}
			renvoi.add(rajout);
		}
		return renvoi;
	}
	
	public ArrayList<Set<ArrayList<String>>> getAllKeys(){
		ArrayList<Set<ArrayList<String>>> allKeys=new ArrayList<Set<ArrayList<String>>>();
		for(Conversation conv : this.conversations) {
			allKeys.add(new HashSet<ArrayList<String>>(conv.getChoosedKeys()));
		}
		return allKeys;
		}
	
	public static Set<String> getAllAssignments(ConversationSet convSet, ArrayList<Set<String>> allKeys){
		Set<String> allAssignments=new HashSet<String>();
		for(Conversation conv : convSet.getConversationSet()) {
			ArrayList<String> Array=new ArrayList<String>(conv.assignments);
			for(String assignInConv : Array) {
				String assignKey = assignInConv.split("=")[0];
				boolean tryAllKeys=false;
				for (Set<String> keyOneByOne : allKeys) {
					if (keyOneByOne.contains(assignKey)) {
						tryAllKeys=true;
					}
				}
				if(!tryAllKeys) {
					allAssignments.add(assignKey);
				}
			}
		}
		return allAssignments;
	}
	
}
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


public class Conversation{
  public ArrayList<Event> conv;

  public ArrayList<ArrayList<String>> choosedKeys;

  public ArrayList<String> assignments;
  public ArrayList<String> cle;

  public int nbRequest;
  public int nbResponse;
  
  public Conversation(){
      conv= new ArrayList<Event>();

      choosedKeys= new ArrayList<ArrayList<String>>();

      nbRequest=0;
      nbResponse=0;
      assignments= new ArrayList<String>();
      
      cle=new ArrayList<String>();;
  }

  public Conversation(Conversation conversation){
      conv= new ArrayList<Event>();
      for (Event ev : conversation.conv) {
    	  conv.add(ev);
      }
 
      choosedKeys= new ArrayList<ArrayList<String>>();
      for(ArrayList<String> choisi : conversation.choosedKeys) {
    	  choosedKeys.add(choisi);
      }

      nbRequest=conversation.nbRequest;
      nbResponse=conversation.nbResponse;
      assignments =  new ArrayList<String>();

      for (String assi : conversation.assignments) {
    	  assignments.add(assi);
      }

      cle=new ArrayList<String>();;
  }

  //renvoie la taille de la conversation
  public int size() {
      return this.conv.size();
  }
  public void nouvelEvent(Event evenement ) {
    this.conv.add(evenement);
  }

  //renvoie la conversation
  public ArrayList<Event> getConv(){
     //Collections.unmodifiableList(conv);
	  return conv;
  }
}

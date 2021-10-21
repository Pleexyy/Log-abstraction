package model;

import java.util.HashMap;

public class Event {
    private int idEvent;
    HashMap<String,String> hashMap = new HashMap<>();

    public Event(int i){
        idEvent = i;
    }

    public void put(String k, String v){
        hashMap.put(k,v);
    }

    @Override
    public String toString(){
        return "Event " + idEvent + " :\n" + hashMap + '\n';
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Event e){
            return this.idEvent == e.idEvent && hashMap.equals(e.hashMap);
        }else
            return false;
    }

    public String getHashMap(String s) {
        return hashMap.get(s);
    }
}

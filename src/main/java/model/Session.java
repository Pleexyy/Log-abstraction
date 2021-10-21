package model;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private int idSession;
    private final List<Event> events = new ArrayList<>();

    public Session(int idSession) {
        this.idSession = idSession;
    }

    public void add(Event e){
        events.add(e);
    }

    public int getIdSession() {
        return idSession;
    }
}

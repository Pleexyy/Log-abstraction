package factory;

import model.Conversation;
import model.ConversationSet;
import model.Event;
import model.Tableau;

public class TableauFactory {
    public Tableau createTableau(ConversationSet convSet){
        Tableau tab = new Tableau();
        for (Conversation c: convSet.getConversationSet()) {
            for (Event e: c.getConv()) {
                StringBuilder buff = new StringBuilder();
                for (String str: e.getParams()) {
                    buff.append(str).append(',');
                }
                tab.addColumn(buff.toString());
            }
            tab.addRow(c.toString());
        }
        return tab;
    }
}

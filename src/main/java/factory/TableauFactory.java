package factory;

import model.Conversation;
import model.ConversationSet;
import model.Event;
import model.Tableau;

public class TableauFactory {
    public static Tableau createTableau(ConversationSet convSet){
        Tableau tab = new Tableau();
        for (Conversation c: convSet.getConversationSet()) {
            tab.addRow(c.toString());
            for (Event e: c.getConv()) {
                StringBuilder buff = new StringBuilder();
                for (String str: e.getParams()) {
                    buff.append(str).append(',');
                }
                int column = tab.addColumn(e.getEventWithTypes());
                tab.setInMatrix(tab.getSizeRows(),column,e);
            }
        }
        return tab;
    }
}

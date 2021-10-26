import distance.DistanceEvent;
import model.Conversation;
import model.ConversationSet;
import model.Event;
import parser.LogParser;
import smile.clustering.DBSCAN;
import smile.data.DataFrame;
import smile.plot.swing.ScatterPlot;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Prog {

    private static ConversationSet convSet;

    public static void main(String[] args) {
        try {
            var res = Prog.class.getResource("/data/log-block.txt");
            if (res != null) {
                convSet = new LogParser().parseFile(new File(res.getFile()));
                if (convSet == null) {
                    System.err.println("Ça n'a pas fonctionné");
                }
                dbscan(convSet);
            } else {
                throw new IOException("L'URL de la ressource est null");
            }
            System.out.println(convSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dbscan(ConversationSet s){
        List<Event> event = new ArrayList<>();
        for (Conversation conv : s.getConversationSet()) {
           event.addAll(conv.getConv());
        }
        var dbscan = DBSCAN.<Event>fit(event.toArray(new Event[0]),new DistanceEvent(),4,4);
        var dataframe = DataFrame.of(event,Event.class);
        try {
            ScatterPlot.of(dataframe, "essaie","autre","last", '.').canvas().window();
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

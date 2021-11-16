import factory.MatriceDistanceFactory;
import model.AbstacteurSession;
import model.ConversationSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.LogParser;

import java.io.File;
import java.io.IOException;

public class MatriceDistanceFactoryTest2 {
    @Test
    public void testDistanceMatrixOnLogCopyPaste() {
        var url = getClass().getResource("data/log-copy-paste.txt");
        Assertions.assertNotNull(url);
        try {
            ConversationSet conversationSet = new LogParser().parseFile(new File(url.getFile()));
            Assertions.assertNotNull(conversationSet);
            var sessionsAbs = new AbstacteurSession().abstracteur(conversationSet);

            for (var anAbstract : sessionsAbs) {
                System.out.println("types: " + anAbstract.getTypage() + " refs:" + anAbstract.getRefs().size());
            }

            var m = MatriceDistanceFactory.createMatrixDistance(sessionsAbs, 4, 0.3);
            System.out.println(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDistanceMatrixOnBigSession() {
        try {
            var url = getClass().getResource("data/bigSession0.txt");
            Assertions.assertNotNull(url);
            ConversationSet conversationSet = new LogParser().parseFile(new File(url.getFile()));
            Assertions.assertNotNull(conversationSet);
            var sessionsAbs = new AbstacteurSession().abstracteur(conversationSet);

            for (var anAbstract : sessionsAbs) {
                System.out.println("types: " + anAbstract.getTypage() + " refs:" + anAbstract.getRefs().size());
            }

            var m = MatriceDistanceFactory.createMatrixDistance(sessionsAbs, 6, 20, 0.9);
            System.out.println(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

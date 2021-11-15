import model.AbstacteurSession;
import model.ConversationSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.LogParser;

import java.io.File;
import java.io.IOException;

public class AbstracteurTest {

    private ConversationSet conversationSet;

    @BeforeEach
    public void init() {
        var url = getClass().getResource("data/logExemple1.txt");
        Assertions.assertNotNull(url);
        ConversationSet conversationSet = null;
        try {
            conversationSet = new LogParser().parseFile(new File(url.getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(conversationSet);
        this.conversationSet = conversationSet;
    }

    @Test
    public void testAbstract() {
        AbstacteurSession abstacteurSession = new AbstacteurSession();
        var sessionsAbstracts = abstacteurSession.abstracteur(conversationSet);
        Assertions.assertNotNull(sessionsAbstracts);
        Assertions.assertNotEquals(conversationSet.getConversationSet().size(), sessionsAbstracts.size());
        for (var x : sessionsAbstracts) {
            System.out.println("types: " + x.getTypage() + " refs:" + x.getRefs());
        }
    }

}

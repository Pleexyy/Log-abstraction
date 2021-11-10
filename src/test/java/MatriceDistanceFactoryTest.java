import factory.MatriceDistanceFactory;
import model.AbstacteurSession;
import model.ConversationSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.LogParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MatriceDistanceFactoryTest {

    private ConversationSet conversationSet;
    private AbstacteurSession abstacteurSession;

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
        abstacteurSession = new AbstacteurSession();
    }

    @Test
    public void testCreateMatrixDistance() {
        int expectedSize = 4 * 4;
        var sessionsAbstracts = abstacteurSession.abstracteur(conversationSet);
        var md = MatriceDistanceFactory.createMatrixDistance(sessionsAbstracts, 1, 1, 0.8);

        Assertions.assertNotNull(md);
        Assertions.assertEquals(md.size(), expectedSize);
    }

    @Test
    public void testCreateLabelList() {
        var sessionsAbstracts = abstacteurSession.abstracteur(conversationSet);
        List<String> typageList = MatriceDistanceFactory.createLabelList(sessionsAbstracts);

        Assertions.assertNotNull(typageList);
        System.out.println(typageList);
    }

}

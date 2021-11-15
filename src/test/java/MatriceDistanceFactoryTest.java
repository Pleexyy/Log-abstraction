import factory.MatriceDistanceFactory;
import model.AbstacteurSession;
import model.ConversationSet;
import model.SessionAbstract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.LogParser;
import smile.math.matrix.Matrix;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MatriceDistanceFactoryTest {

    private final int lookback = 1;
    private final int incrementValue = 1;
    private final double facteurAttenuation = 0.8;
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
        List<SessionAbstract> sessionsAbstracts = abstacteurSession.abstracteur(conversationSet);
        Matrix md = MatriceDistanceFactory.createMatrixDistance(sessionsAbstracts, lookback, incrementValue, facteurAttenuation);

        Assertions.assertNotNull(md);
        Assertions.assertEquals(md.size(), expectedSize);
    }

    @Test
    public void testCreateLabelList() {
        List<SessionAbstract> sessionsAbstracts = abstacteurSession.abstracteur(conversationSet);
        List<String> typageList = MatriceDistanceFactory.createLabelList(sessionsAbstracts);

        Assertions.assertNotNull(typageList);
        System.out.println(typageList);
    }

    @Test
    public void testDiagonalOfZero() {
        List<SessionAbstract> sessionsAbstracts = abstacteurSession.abstracteur(conversationSet);
        Matrix md = MatriceDistanceFactory.createMatrixDistance(sessionsAbstracts, lookback, incrementValue, facteurAttenuation);

        for (int i = 0; i < md.nrows(); i++) {
            for (int j = 0; j < md.ncols(); j++) {
                if (i == j) {
                    Assertions.assertEquals(md.get(i, j), 0);
                }
            }
        }
    }

}

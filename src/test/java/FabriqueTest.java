import factory.TableauFactory;
import junit.framework.TestCase;
import model.ConversationSet;
import model.Tableau;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.LogParser;

import java.io.File;
import java.io.IOException;

public class FabriqueTest extends TestCase {

    private TableauFactory tabFact;

    @BeforeEach
    public void setUp(){
        tabFact = new TableauFactory();
    }

    @Test
    public void testFabrique(){
        try {
            ConversationSet conversationSet = new LogParser().parseFile(new File(getClass().getResource("data/logExemple1.txt").getFile()));
            Assertions.assertNotNull(conversationSet);
            Tableau tab = new TableauFactory().createTableau(conversationSet);
            System.out.println(tab.toStringFormated());
            Assertions.assertNotNull(tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

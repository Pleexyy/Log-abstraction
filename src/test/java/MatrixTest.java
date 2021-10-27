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

public class MatrixTest extends TestCase {

    private Tableau tableau;

    @BeforeEach
    public void init(){
        try {
            var url = getClass().getResource("data/logExemple1.txt");
            Assertions.assertNotNull(url);
            ConversationSet conversationSet = new LogParser().parseFile(new File(url.getFile()));
            Assertions.assertNotNull(conversationSet);
            tableau = TableauFactory.createTableau(conversationSet);
            Assertions.assertNotNull(tableau);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteColumnByName(){
        tableau.printDebugMatrix();
        tableau.deleteColumnName("Verb=String,idx=int,session=int");
        System.out.println("\n\n");
        tableau.printDebugMatrix();
        Assertions.assertNotNull(tableau);
    }

    @Test
    public void testDeleteColumnByName2(){
        tableau.printDebugMatrix();
        tableau.deleteColumnName("group=int,session=int");
        System.out.println("\n\n");
        tableau.printDebugMatrix();
        Assertions.assertNotNull(tableau);
    }
}

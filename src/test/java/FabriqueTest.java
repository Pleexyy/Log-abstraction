import factory.TableauFactory;
import junit.framework.TestCase;
import model.ConversationSet;
import model.Tableau;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.LogParser;

import java.io.File;
import java.io.IOException;

public class FabriqueTest extends TestCase {

    @Test
    public void testFabrique() {
        try {
            var url = getClass().getResource("data/logExemple1.txt");
            Assertions.assertNotNull(url);
            ConversationSet conversationSet = new LogParser().parseFile(new File(url.getFile()));
            Assertions.assertNotNull(conversationSet);
            Tableau tab = TableauFactory.createTableau(conversationSet);
            Assertions.assertNotNull(tab);
            Assertions.assertEquals(2,tab.getSizeRows());

            System.out.println(tab.toStringFormated() + '\n');
            tab.printDebugMatrix();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFabrique2(){
        try{
            var url = getClass().getResource("/data/log-block.txt");
            Assertions.assertNotNull(url);
            ConversationSet convSet = new LogParser().parseFile(new File(url.getFile()));
            Assertions.assertNotNull(convSet);
            Tableau tab = TableauFactory.createTableau(convSet);
            Assertions.assertNotNull(tab);
            Assertions.assertEquals(19,tab.getSizeRows());

            System.out.println(tab.toStringFormated() + '\n');
            tab.printDebugMatrix();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testFabrique3(){
        try{
            var url = getClass().getResource("/data/ConversationSet0.txt");
            Assertions.assertNotNull(url);
            ConversationSet convSet = new LogParser().parseFile(new File(url.getFile()));
            Assertions.assertNotNull(convSet);
            Tableau tab = TableauFactory.createTableau(convSet);
            Assertions.assertNotNull(tab);
            Assertions.assertEquals(875,tab.getSizeRows());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

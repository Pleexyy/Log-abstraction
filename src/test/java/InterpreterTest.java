import junit.framework.TestCase;
import model.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InterpreterTest extends TestCase {

    @Test
    public void testNewEventModel(){
        final String stringedEvent = "e0(Verb=GET,idx=3)";
        Event e = new Event(stringedEvent);
        Assertions.assertEquals("e0",e.getLabel());
        Assertions.assertEquals(stringedEvent,e.toString());
    }
}
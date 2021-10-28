import model.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToStringTest {

    @Test
    public void testToString() {
        final String stringedEvent = "e0(Verb=GET,idx=3)";
        Event event = new Event(stringedEvent);
        Assertions.assertEquals(stringedEvent, event.toString());
    }
}

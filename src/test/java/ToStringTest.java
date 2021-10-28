import model.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToStringTest {

    private Event event;

    @BeforeEach
    public void init() {
        event = new Event();
    }

    @Test
    public void testToString() {
        final String stringedEvent = "e0(Verb=GET,idx=3)";
        event = new Event(stringedEvent);
        Assertions.assertEquals(stringedEvent, event.toString());
    }
}

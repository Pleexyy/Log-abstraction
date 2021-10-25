import junit.framework.Assert;
import model.Event;
import org.junit.jupiter.api.Test;

public class TyperTest {

    @Test
    public void testGetEventWithTypes() {
        final String stringedEvent = "e0(Verb=GET,idx=3)";
        final String expectedEvent = "Verb=String,idx=int";

        Event event = new Event(stringedEvent);
        String eventWithTypes = event.getEventWithTypes();

        Assert.assertEquals(expectedEvent, eventWithTypes);
    }

    @Test
    public void testGetTypedAssignments() {
        Event event = new Event();
        final String param = "GET";
        final String expectedAssignment = "String";
        Assert.assertEquals(event.getTypedAssignments(param), expectedAssignment);
    }

    @Test
    public void testGetParameters() {
        Event event = new Event();
        final String param = "Verb=GET";
        final String expectedParameter = "Verb";

        Assert.assertEquals(event.getParameters(param), expectedParameter);
    }

}

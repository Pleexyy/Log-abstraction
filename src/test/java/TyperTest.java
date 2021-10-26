import junit.framework.Assert;
import model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TyperTest {

    private Event event;

    @BeforeEach
    public void init() {
        event = new Event();
    }

    @Test
    public void testGetParameters() {
        final String param = "Verb=GET";
        final String expectedParameter = "Verb";

        Assert.assertEquals(event.getParameters(param), expectedParameter);
    }

    @Test
    public void testGetTypedAssignmentsString() {
        final String param = "GET";
        final String expectedAssignment = "String";

        Assert.assertEquals(event.getTypedAssignments(param), expectedAssignment);
    }

    @Test
    public void testGetTypedAssignmentsFloat() {
        final String param = "5.5";
        final String expectedAssignment = "float";

        Assert.assertEquals(event.getTypedAssignments(param), expectedAssignment);
    }

    @Test
    public void testGetTypedAssignmentsInteger() {
        final String param = "10";
        final String expectedAssignment = "int";

        Assert.assertEquals(event.getTypedAssignments(param), expectedAssignment);
    }

    @Test
    public void testGetEventWithTypes() {
        final String stringedEvent = "e0(Verb=GET,idx=3)";
        final String expectedEvent = "Verb=String,idx=int";

        event = new Event(stringedEvent);
        String eventWithTypes = event.getEventWithTypes();

        Assert.assertEquals(expectedEvent, eventWithTypes);
    }

}

import model.Conversation;
import model.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ConversationTest {

    private Conversation conversation;

    @BeforeEach
    public void init() {
        conversation = new Conversation();
    }

    @Test
    public void testNouvelEvent() {
        final String stringedEvent = "e0(Verb=GET,idx=3)";
        Event event = new Event(stringedEvent);

        conversation.nouvelEvent(event);

        Assertions.assertTrue(conversation.getConv().contains(event));
    }

    @Test
    public void testgetConv() {
        final String stringedEvent = "e0(Verb=GET,idx=3)";
        Event event = new Event(stringedEvent);

        conversation.nouvelEvent(event);

        final ArrayList<Event> expectedEventList = new ArrayList<>();
        expectedEventList.add(event);

        Assertions.assertEquals(expectedEventList.size(), conversation.getConv().size());
        Assertions.assertEquals(expectedEventList, conversation.getConv());
    }

    @Test
    public void testSize() {
        final int expectedListSize = 1;
        final String stringedEvent = "e0(Verb=GET,idx=3)";
        Event event = new Event(stringedEvent);

        conversation.nouvelEvent(event);

        Assertions.assertEquals(expectedListSize, conversation.size());
    }

}

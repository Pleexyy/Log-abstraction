import model.Event;
import model.SeparationWhenNoRegex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testSeparationWhenNoRegex {
    private SeparationWhenNoRegex separationWhenNoRegex;

    @BeforeEach
    public void init() {
        separationWhenNoRegex = new SeparationWhenNoRegex();
    }

    @Test
    public void testSeparationWhenNoRegex() {
        final String stringedEvent = "e0(Verb=GET,idx=3)";
        final String expectedLabel = "e0";

        Assertions.assertEquals(expectedLabel, separationWhenNoRegex.nameOfAnEvent(stringedEvent));
    }

}

import model.SeparationWhenNoRegex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testSeparationWhenNoRegex {

    @Test
    public void testNameOfAnEvent() {
        final String stringedEvent = "e0(Verb=GET,idx=3)";
        final String expectedLabel = "e0";

        Assertions.assertEquals(expectedLabel, SeparationWhenNoRegex.nameOfAnEvent(stringedEvent));
    }

    @Test
    public void separationWithoutRegex() {
        final String stringedEvent = "e0(Verb=GET,idx=3)";
        final ArrayList<String> expectedParam = new ArrayList<>();
        expectedParam.add("Verb=GET");
        expectedParam.add("idx=3");

        Assertions.assertEquals(expectedParam.size(), SeparationWhenNoRegex.separationWithoutRegex(stringedEvent).size());
        Assertions.assertEquals(expectedParam, SeparationWhenNoRegex.separationWithoutRegex(stringedEvent));
    }

}

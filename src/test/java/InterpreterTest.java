import interpret.Interpreter;
import junit.framework.TestCase;
import model.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class InterpreterTest extends TestCase {

    private static Interpreter interpreter;

    @BeforeAll
    public static void init(){
        interpreter = new Interpreter();
    }

    @Test
    public void onEventSimple(){
        Event event = new Event(1);
        event.put("status","200");
        event.put("response","OK");
        event.put("type","application/json");
        event.put("session","1");
        Event e = interpreter.interpreteEvent("e1(status=200, response=OK, type=application/json, session=1);");
        Assertions.assertNotNull(e);
        Assertions.assertEquals(event,e);
    }

    @Test
    public void oneEventComplexe(){
        Event event = new Event(0);
        event.put("Verb","GET");
        event.put("Uri","/json.htm");
        event.put("type","command");
        event.put("param","switchlight");
        event.put("idx","10");
        event.put("switchcmd","Off");
        event.put("rssi","");
        event.put("session","14");
        Event result = interpreter.interpreteEvent("e0(Verb=GET, Uri=/json.htm, type=command, param=switchlight, idx=10, switchcmd=Off, rssi, session=14);");
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(event.getHashMap("rssi"));
        Assertions.assertEquals(event,result);
    }
}

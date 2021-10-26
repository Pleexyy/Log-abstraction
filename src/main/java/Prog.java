import model.ConversationSet;
import parser.LogParser;

import java.io.File;
import java.io.IOException;

public class Prog {

    private static ConversationSet convSet;

    public static void main(String[] args) {
        try {
            var res = Prog.class.getResource("/data/log-block.txt");
            if (res != null) {
                convSet = new LogParser().parseFile(new File(res.getFile()));
                if (convSet == null) {
                    System.err.println("Ça n'a pas fonctionné");
                }
            } else {
                throw new IOException("L'URL de la ressource est null");
            }
            System.out.println(convSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

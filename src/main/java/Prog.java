import interpret.Interpreter;

import java.io.File;
import java.io.IOException;

public class Prog {
    public static void main(String[] args) {
        try {
            var res = Prog.class.getResource("/data/log-block.txt");
            if (res != null) {
                int i = new Interpreter().readFile(new File(res.getFile()));
                if (i == -1){
                    System.err.println("Ça n'a pas fonctionné");
                }
            }
            else{
                throw new IOException("L'URL de la ressource est null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

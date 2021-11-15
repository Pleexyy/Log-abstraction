import factory.MatriceDistanceFactory;
import model.AbstacteurSession;
import model.ConversationSet;
import parser.LogParser;
import smile.clustering.DBSCAN;
import smile.math.matrix.Matrix;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Prog {

    private static final double facteurAttenuation = 0.8;
    private static ConversationSet convSet;

    public static void main(String[] args) {
        try {
            var res = Prog.class.getResource("/data/ConversationSet0.txt");
            if (res != null) {
                convSet = new LogParser().parseFile(new File(res.getFile()));
            } else {
                throw new IOException("L'URL de la ressource est null");
            }
            var sessionsAbstracts = new AbstacteurSession().abstracteur(convSet);
            for (var x : sessionsAbstracts) {
                System.out.println("types: " + x.getTypage() + " refs:" + x.getRefs().size());
            }
            Matrix m = MatriceDistanceFactory.createMatrixDistance(sessionsAbstracts, 1, facteurAttenuation);
            System.out.println(m);
            var dbscan = DBSCAN.fit(m.toArray(), 4, 1);
            System.out.println("nombre de cluster(s) pour un facteur d'atténuation de " + facteurAttenuation + " : " + dbscan.k);
            System.out.println(Arrays.toString(dbscan.y));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

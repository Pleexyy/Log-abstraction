import factory.MatriceDistanceFactory;
import factory.TableauFactory;
import model.AbstacteurSession;
import model.ConversationSet;
import model.Tableau;
import parser.LogParser;
import smile.clustering.DBSCAN;
import smile.math.matrix.Matrix;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Prog {

    private static ConversationSet convSet;
    private static final double facteurAttenuation = 0.8;

    public static void main(String[] args) {
        try {
            var res = Prog.class.getResource("/data/log-block.txt");
            if (res != null) {
                convSet = new LogParser().parseFile(new File(res.getFile()));
            } else {
                throw new IOException("L'URL de la ressource est null");
            }
            Tableau t = TableauFactory.createTableau(convSet);
            Matrix m = MatriceDistanceFactory.createMatrixDistance(t, new AbstacteurSession().abstracteur(convSet), 1, facteurAttenuation);
            System.out.println(m);
            var dbscan = DBSCAN.fit(m.toArray(), 1, 1);
            System.out.println("nombre de cluster(s) pour un facteur d'atténuation de " + facteurAttenuation + " : " + dbscan.k);
            System.out.println(Arrays.toString(dbscan.y));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

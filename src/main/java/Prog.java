import factory.MatriceDistanceFactory;
import model.AbstacteurSession;
import model.ConversationSet;
import parser.LogParser;
import smile.clustering.DBSCAN;
import smile.clustering.PartitionClustering;
import smile.math.matrix.Matrix;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Prog {

    private static final double facteurAttenuation = 0.8;
    private static ConversationSet convSet;

    public static void main(String[] args) {
        try {
            var res = Prog.class.getResource("/data/test.txt");
            if (res != null) {
                convSet = new LogParser().parseFile(new File(res.getFile()));
            } else {
                throw new IOException("L'URL de la ressource est null");
            }
            var sessionsAbstracts = new AbstacteurSession().abstracteur(convSet);
            for (var x : sessionsAbstracts) {
                System.out.println("types: " + x.getTypage() + " refs:" + x.getRefs().size());
            }
            Matrix m = MatriceDistanceFactory.createMatrixDistance(sessionsAbstracts, 7, 20, facteurAttenuation);
            System.out.println(m);
            var dbscan = DBSCAN.fit(m.toArray(), 2, 20);

            printVerboseHuman(dbscan);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printVerboseHuman(DBSCAN<double[]> dbscan) {
        System.out.println("nombre de cluster(s) pour un facteur d'atténuation de " + facteurAttenuation + " : " + dbscan.k);

        int nbNoise = dbscan.size[dbscan.size.length - 1];
        System.out.println("Il y a " + nbNoise + " événement(s) abstrait(s) qui sont du bruits");
        List<String> abstratEventNoise = new ArrayList<>();

        for (int i = 0; i < dbscan.y.length; i++) {
            if (dbscan.y[i] == PartitionClustering.OUTLIER) {
                abstratEventNoise.add(MatriceDistanceFactory.getListeLabel().get(i));
            }
        }

        System.out.println("Il s'agit de : " + abstratEventNoise);
    }

}

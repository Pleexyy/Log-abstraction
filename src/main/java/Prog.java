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

public class Prog {

    private static final double facteurAttenuation = 0.7;
    private static final int incrementValue = 20;
    private static ConversationSet convSet;

    public static void main(String[] args) {
        try {
            var res = Prog.class.getResource("data/ConversationSet5.txt");
            if (res != null) {
                convSet = new LogParser().parseFile(new File(res.getFile()));
            } else {
                throw new IOException("L'URL de la ressource est null");
            }
            var sessionsAbstracts = new AbstacteurSession().abstracteur(convSet);
            for (var s : sessionsAbstracts) {
                System.out.println(s.getTypage() + " refs:" + s.getRefs().size() + '\n');
            }
            Matrix m = MatriceDistanceFactory.createMatrixDistance(sessionsAbstracts, 7, incrementValue, facteurAttenuation);
            System.out.println("\n" + m);
            var dbscan = DBSCAN.fit(m.toArray(), 1, 0.059);

            printVerboseHuman(dbscan);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param dbscan type double
     */
    private static void printVerboseHuman(DBSCAN<double[]> dbscan) {
        ArrayList<String>[] abstractEventClustered = new ArrayList[dbscan.k];
        for (int i = 0; i < abstractEventClustered.length; i++) {
            abstractEventClustered[i] = new ArrayList<>();
        }
        // List<String> abstractEventNoise = new ArrayList<>();

        for (int i = 0; i < dbscan.y.length; i++) {
            String strEvtAbs = MatriceDistanceFactory.getListeLabel().get(i);
            int clusterLabel = dbscan.y[i];
            if (clusterLabel == PartitionClustering.OUTLIER) {
                // si un label correspond à du bruit, on l'ajoute à la liste
                // abstractEventNoise.add(strEvtAbs);
            } else {
                // sinon on l'ajoute dans sa liste d'événement clusterisé
                abstractEventClustered[clusterLabel].add(strEvtAbs);
            }
        }
        System.out.println("Il y a " + dbscan.k + " cluster(s) :");

        for (int i = 0; i < abstractEventClustered.length; i++) {
            System.out.println("cluster n°"+i);
            System.out.println("\t"+abstractEventClustered[i]);
        }

        int nbNoise = dbscan.size[dbscan.size.length - 1];
        System.out.println("\nIl y a " + nbNoise + " événement(s) abstrait(s) qui est/sont considéré(s) comme du bruit");
        // System.out.println("Il s'agit de : " + abstractEventNoise);
    }

}

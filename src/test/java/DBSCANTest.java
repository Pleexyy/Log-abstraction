import factory.MatriceDistanceFactory;
import model.AbstacteurSession;
import model.ConversationSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.LogParser;
import smile.clustering.DBSCAN;
import smile.math.matrix.Matrix;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class DBSCANTest {

    private void printInfoDBSCAN(DBSCAN<double[]> dbscan) {
        System.out.println("DBSCAN : nb cluster : "+dbscan.k);
        System.out.println("DBSCAN : type des clusters : "+ Arrays.toString(dbscan.y));
        System.out.println("Voici aussi la taille : "+ Arrays.toString(dbscan.size));
    }

    private void symetricMatrix(Matrix m) {
        for (int i = m.nrows()-1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                m.set(j,i,m.get(i,j));
            }
        }
    }

    @Test
    public void testDistanceManuallyCreated() {
        Matrix m = new Matrix(5,5);

        m.set(1,0,1.6);
        m.set(2,1,1.6);
        m.set(3,2,1.6);
        m.set(4,3,0.8);

        for (int i = 4; i > 0; i--) {
            for (int j = 4; j > 0; j--) {
                m.set(j,i,m.get(i,j));
            }
        }

        System.out.println(m);

        printInfoDBSCAN(DBSCAN.fit(m.toArray(),1,1));
    }

    @Test
    public void testDBSCANOnBigSession() {
        try {
            var url = getClass().getResource("data/bigSession0.txt");
            Assertions.assertNotNull(url);
            ConversationSet conversationSet = new LogParser().parseFile(new File(url.getFile()));
            Assertions.assertNotNull(conversationSet);
            var sessionsAbs = new AbstacteurSession().abstracteur(conversationSet);
            var m = MatriceDistanceFactory.createMatrixDistance(sessionsAbs,7,20,0.9);
            System.out.println(m);

            printInfoDBSCAN(DBSCAN.fit(m.toArray(),2,2000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWithDistanceMatrixFromInternet() {
        Matrix m = new Matrix(12,12);

        m.set(1,0,20);
        m.set(2,0,20);
        m.set(3,0,20);
        m.set(4,0,40);
        m.set(5,0,60);
        m.set(6,0,60);
        m.set(7,0,60);
        m.set(8,0,100);
        m.set(9,0,120);
        m.set(10,0,120);
        m.set(11,0,120);

        m.set(2,1,20);
        m.set(3,1,20);
        m.set(4,1,60);
        m.set(5,1,80);
        m.set(6,1,80);
        m.set(7,1,80);
        m.set(8,1,120);
        m.set(9,1,120);
        m.set(10,1,140);
        m.set(11,1,140);

        m.set(3,2,20);
        m.set(4,2,60);
        m.set(5,2,80);
        m.set(6,2,80);
        m.set(7,2,80);
        m.set(8,2,120);
        m.set(9,2,140);
        m.set(10,2,140);
        m.set(11,2,140);

        m.set(4,3,60);
        m.set(5,3,80);
        m.set(6,3,80);
        m.set(7,3,80);
        m.set(8,3,120);
        m.set(9,3,140);
        m.set(10,3,140);
        m.set(11,3,140);

        m.set(5,4,20);
        m.set(6,4,20);
        m.set(7,4,20);
        m.set(8,4,60);
        m.set(9,4,80);
        m.set(10,4,80);
        m.set(11,4,80);

        m.set(6,5,20);
        m.set(7,5,20);
        m.set(8,5,40);
        m.set(9,5,60);
        m.set(10,5,60);
        m.set(11,5,60);

        m.set(7,6,20);
        m.set(8,6,60);
        m.set(9,6,80);
        m.set(10,6,80);
        m.set(11,6,80);

        m.set(8,7,60);
        m.set(9,7,80);
        m.set(10,7,80);
        m.set(11,7,80);

        m.set(9,8,20);
        m.set(10,8,20);
        m.set(11,8,20);

        m.set(10,9,20);
        m.set(11,9,20);

        m.set(11,10,20);

        symetricMatrix(m);

        System.out.println(m);

        printInfoDBSCAN(DBSCAN.fit(m.toArray(),2,80));
    }
}

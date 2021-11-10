import org.junit.jupiter.api.Test;
import smile.clustering.DBSCAN;
import smile.math.matrix.Matrix;

import java.util.Arrays;

public class DBSCANTest {
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

        var x = DBSCAN.fit(m.toArray(),1,1);
        System.out.println("DBSCAN : nb cluster : "+x.k);
        System.out.println("DBSCAN : type des clusters : "+ Arrays.toString(x.y));
        System.out.println("Voici aussi la taille : "+ Arrays.toString(x.size));
    }
}

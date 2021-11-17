package factory;

import model.SessionAbstract;
import smile.math.matrix.Matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatriceDistanceFactory {

    private static List<String> typage;

    /**
     * @return typage la liste des labels
     */
    public static List<String> getListeLabel() {
        return Collections.unmodifiableList(typage);
    }

    public static Matrix createMatrixDistance(List<SessionAbstract> sessionAbstracts, final int lookback, final double facteurAttenuation) {
        return createMatrixDistance(sessionAbstracts, lookback, 1, facteurAttenuation);
    }

    /**
     * @param sessionAbstracts   liste de sessions abstraites
     * @param lookback           fenêtre de recherche
     * @param incrementValue     valeur d'incrémentation
     * @param facteurAttenuation facteur d'atténuation compris entre 0 et 1
     * @return m la matrice de distance
     */
    public static Matrix createMatrixDistance(List<SessionAbstract> sessionAbstracts, final int lookback, final int incrementValue, final double facteurAttenuation) {
        if (lookback < 0 || incrementValue < 0)
            throw new IllegalArgumentException("la lookback ou la valeur d'incrémentation est négatif");
        if (facteurAttenuation <= 0 || facteurAttenuation > 1)
            throw new IllegalArgumentException("le facteur d'atténuation doit être compris entre 0 et 1");

        List<String> typageLabel = createLabelList(sessionAbstracts);
        Matrix m = new Matrix(typageLabel.size(), typageLabel.size());

        for (SessionAbstract sabs : sessionAbstracts) {
            for (int i = 1; i < sabs.getTypage().size(); i++) {
                String ref = sabs.getTypage().get(i);
                int rowIndex = typageLabel.indexOf(ref);
                for (int j = 1; j <= lookback; j++) {
                    if (i >= j) {
                        String eventY = sabs.getTypage().get(i - j);
                        // on trouve l'emplacement où prendre et mettre la valeur
                        int columnIndex = typageLabel.indexOf(eventY);
                        // pour ne pas faire l'operation sur la diagonale
                        if (rowIndex == columnIndex)
                            continue;
                        // on obtient la distance
                        double distanceValue = m.get(rowIndex, columnIndex) + incrementValue * Math.pow(facteurAttenuation, j);

                        // notion de symétrie
                        m.set(rowIndex, columnIndex, distanceValue);
                        m.set(columnIndex, rowIndex, distanceValue);
                    }
                }
            }
        }
        inverseMatrix(m);
        return m;
    }

    /**
     * @param m la matrice de distance
     */
    private static void inverseMatrix(Matrix m) {
        for (int i = 0; i < m.nrows(); i++) {
            for (int j = 0; j < m.ncols(); j++) {
                // on calcule la distance pour tous les éléments sauf lorsque rowIndex == columnIndex
                if (j != i) {
                    double nval = 1.0 / m.get(i, j);
                    if (Double.POSITIVE_INFINITY == nval) {
                        nval = Double.MAX_VALUE;
                    }
                    m.set(i, j, nval);
                }
            }
        }
    }

    /**
     * @param sessionAbstractList liste de sessions abstraites
     * @return typage la liste des labels
     */
    public static List<String> createLabelList(List<SessionAbstract> sessionAbstractList) {
        typage = new ArrayList<>();
        for (SessionAbstract s : sessionAbstractList) {
            for (String type : s.getTypage()) {
                if (!typage.contains(type)) {
                    typage.add(type);
                }
            }
        }
        return typage;
    }
}

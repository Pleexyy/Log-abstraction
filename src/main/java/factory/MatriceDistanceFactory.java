package factory;

import model.SessionAbstract;
import smile.math.matrix.Matrix;

import java.util.ArrayList;
import java.util.List;

public class MatriceDistanceFactory {

    public static Matrix createMatrixDistance(List<SessionAbstract> sessionAbstracts, final int lookback, final double facteurAttenuation) {
        return createMatrixDistance(sessionAbstracts, lookback, 1, facteurAttenuation);
    }

    public static Matrix createMatrixDistance(List<SessionAbstract> sessionAbstracts, final int lookback, final int incrementValue, final double facteurAttenuation) {
        if (lookback < 0 || incrementValue < 0)
            throw new IllegalArgumentException("loopback ou incrementValue est négatif");
        if (facteurAttenuation <= 0 || facteurAttenuation > 1)
            throw new IllegalArgumentException("facteurAttenuation doit être compris entre 1 et 0");

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
                        // on obtient la distance
                        double distanceValue = 1.0 / m.get(rowIndex, columnIndex) + incrementValue * Math.pow(facteurAttenuation, j);

                        // 1/0 -> affichage valeur max d'un double
                        if (Double.POSITIVE_INFINITY == distanceValue) {
                            distanceValue = Double.MAX_VALUE;
                        }

                        m.set(rowIndex, columnIndex, distanceValue);
                        m.set(columnIndex, rowIndex, distanceValue);
                    }
                }
            }
        }
        return m;
    }

    public static List<String> createLabelList(List<SessionAbstract> sessionAbstractList) {
        List<String> typageList = new ArrayList<>();
        for (SessionAbstract s : sessionAbstractList) {
            for (String type : s.getTypage()) {
                if (!typageList.contains(type)) {
                    typageList.add(type);
                }
            }
        }
        return typageList;
    }
}

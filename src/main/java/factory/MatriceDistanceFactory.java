package factory;

import model.Conversation;
import model.ConversationSet;
import model.Event;
import model.Tableau;
import smile.math.matrix.Matrix;

public class MatriceDistanceFactory {

    public static Matrix createMatrixDistance(Tableau t, ConversationSet convSet, final int lookback, final double facteurAttenuation) {
        return createMatrixDistance(t, convSet, lookback, 1, facteurAttenuation);
    }

    public static Matrix createMatrixDistance(Tableau t, ConversationSet convSet, final int lookback, final int incrementValue, final double facteurAttenuation) {
        if (lookback < 0 || incrementValue < 0)
            throw new IllegalArgumentException("loopback ou incrementValue est négatif");
        if (facteurAttenuation <= 0 || facteurAttenuation > 1)
            throw new IllegalArgumentException("facteurAttenuation doit être compris entre 1 et 0");
        Matrix m;
        {
            int tailleColumn = t.getSizeColumns();
            m = new Matrix(tailleColumn, tailleColumn);
        }
        for (Conversation conv : convSet.getConversationSet()) {
            int size = conv.size();
            for (int i = 0; i < size; i++) {
                Event refEvent = conv.getConv().get(i);
                int rowIndex = t.columnIndexOf(refEvent.getEventWithTypes());
                for (int j = 1; j <= lookback; j++) {
                    if (i >= j) {
                        Event eventY = conv.getConv().get(i - j);
                        // on trouve l'emplacement où prendre et mettre la valeur
                        int columnIndex = t.columnIndexOf(eventY.getEventWithTypes());
                        // on obtient la distance
                        double distanceValue = m.get(rowIndex, columnIndex) + incrementValue * Math.pow(facteurAttenuation, j);

                        m.set(rowIndex, columnIndex, distanceValue);
                        m.set(columnIndex, rowIndex, distanceValue);
                    }
                }
            }
        }
        divideDistanceMatrix(m);
        return m;
    }

    public static void divideDistanceMatrix(Matrix matrix){
        for (int row = 0; row < matrix.nrows(); row++) {
            for (int col = 0; col < matrix.ncols(); col++) {
                double value = 1.0/matrix.get(row,col);
                if (value == Double.POSITIVE_INFINITY){
                    value = 0;
                }
                matrix.set(row,col,value);
            }
        }
    }
}

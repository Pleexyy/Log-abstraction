package factory;

import model.Conversation;
import model.ConversationSet;
import model.Event;
import model.Tableau;
import smile.math.matrix.Matrix;

public class MatriceDistanceFactory {

    public static Matrix createMatrixDistance(Tableau t, ConversationSet convSet, final int lookback, final double facteurAttenuation){
        return createMatrixDistance(t,convSet,lookback,1,facteurAttenuation);
    }

    public static Matrix createMatrixDistance(Tableau t, ConversationSet convSet, final int lookback, final int incrementValue, final double facteurAttenuation){
        if (lookback < 0 || incrementValue < 0)
            throw new IllegalArgumentException("loopback ou incrementValue est négatif");
        if (facteurAttenuation <= 0 || facteurAttenuation > 1)
            throw new IllegalArgumentException("facteurAttenuation doit être compris entre 1 et 0");
        Matrix m;
        {
            int tailleColumn = t.getSizeColumns()+1;
            m = new Matrix(tailleColumn, tailleColumn);
        }
        for (Conversation conv : convSet.getConversationSet()){
            int size = conv.size();
            for (int i = 0; i < size; i++) {
                Event refEvent = conv.getConv().get(i);
                for (int j = 1; j <= lookback; j++) {
                    if (i >= j) {
                        Event eventY = conv.getConv().get(i - j);
                        // on trouve l'emplacement où prendre et mettre la valeur
                        int rowIndex = t.columnIndexOf(refEvent.getEventWithTypes());
                        int columnIndex = t.columnIndexOf(eventY.getEventWithTypes());
                        // on obtient la distance
                        double distanceValue = 1.0/m.get(rowIndex,columnIndex) + incrementValue * Math.pow(facteurAttenuation, j);

                        // 1/0 -> affichage valeur max d'un double
                        if (Double.POSITIVE_INFINITY == distanceValue){
                            m.set(rowIndex,columnIndex,Double.MAX_VALUE);
                            m.set(columnIndex,rowIndex,Double.MAX_VALUE);
                        }else{
                            m.set(rowIndex,columnIndex,distanceValue);
                            m.set(columnIndex,rowIndex,distanceValue);
                        }
                    }
                }
            }
        }
        return m;
    }
}

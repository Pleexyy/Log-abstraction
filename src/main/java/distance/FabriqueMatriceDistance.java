package distance;

import model.Conversation;
import model.ConversationSet;
import model.Event;
import model.Tableau;
import smile.math.matrix.Matrix;

public class FabriqueMatriceDistance {

    public static Matrix createMatrixDistance(Tableau t, ConversationSet convSet, final int lookback, final int incrementValue, final double facteurAttenuation){
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
                        // on trouve l'emplacement où mettre la valeur
                        int rowIndex = t.columnIndexOf(refEvent.getEventWithTypes());
                        int columnIndex = t.columnIndexOf(eventY.getEventWithTypes());
                        // on obtient la distance
                        double distanceValue = m.get(rowIndex,columnIndex) + incrementValue * Math.pow(facteurAttenuation, j);
                        //on l'affecte
                        m.set(rowIndex,columnIndex,distanceValue);
                    }
                }
            }
        }
        return m;
    }

    private static double getDistanceValue(Event refEvent, Event eventY) {
        return 0d;
    }
}

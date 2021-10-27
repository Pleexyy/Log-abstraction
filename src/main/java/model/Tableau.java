package model;

import java.util.ArrayList;
import java.util.List;

public class Tableau {
    private final List<String> labelRow = new ArrayList<>();
    private final List<String> labelColumn = new ArrayList<>();
    private Matrix matrix;

    public Tableau() {
        matrix = new Matrix(1, 1);
    }

    public void addRow(String row) {
        labelRow.add(row);
        if (matrix.get(0, 0) != null)
            matrix = matrix.extend(1, 0);
    }

    public int addColumn(String column) {
        int i = labelColumn.indexOf(column);
        if (i == -1) {
            labelColumn.add(column);
            if (matrix.get(0, 0) != null) {
                matrix = matrix.extend(0, 1);
            }
            return labelColumn.size() - 1;
        }
        return i;
    }

    public int getSizeRows() {
        return labelRow.size() - 1;
    }

    public int getSizeColumns(){
        return labelColumn.size() - 1;
    }

    public String toStringFormated() {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(" ".repeat(labelRow.get(0).length()));
        for (String s : labelColumn) {
            stringBuffer.append(s).append('\t');
        }
        stringBuffer.append('\n');
        for (int j = 0; j < labelRow.size(); ++j) {
            stringBuffer.append(labelRow.get(j));
            for (int i = 0; i < labelColumn.size(); ++i) {
                if (matrix.get(j, i) == null) {
                    stringBuffer.append(" ".repeat(labelColumn.get(i).length()));
                } else {
                    String replacement = " ".repeat(labelColumn.get(i).length() / 2);
                    stringBuffer.append(replacement).append(matrix.get(j, i).getLabel()).append(replacement);
                }
                stringBuffer.append('\t');
            }
            stringBuffer.append('\n');
        }
        return stringBuffer.toString();
    }

    public void setInMatrix(int raw, int col, Event e) {
        matrix.set(raw, col, e);
    }

    public void printDebugMatrix() {
        System.out.println(matrix);
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void deleteColumnNb(int nb){
        labelColumn.remove(nb);
        matrix = matrix.removeColumnNb(nb);
    }

    public void deleteColumnName(String str) {
        int size = labelColumn.size();
        for(int i = 0; i < size ; ++i){
            System.out.println(i);
            if (labelColumn.get(i).equals(str)){
                deleteColumnNb(i);
                return;
            }
        }
    }

    public int columnIndexOf(String type) {
        return labelColumn.indexOf(type);
    }
}

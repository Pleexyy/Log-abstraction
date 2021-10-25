package model;

import java.util.ArrayList;
import java.util.List;

public class Tableau {
    private final List<String> labelRow = new ArrayList<>();
    private final List<String> labelColumn = new ArrayList<>();

    public boolean addRow(String row){
        if (!labelRow.contains(row)){
            return labelRow.add(row);
        }
        return false;
    }

    public boolean addColumn(String column){
        if (!labelColumn.contains(column)){
            return labelColumn.add(column);
        }
        return false;
    }

    public String toStringFormated(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" ".repeat(labelRow.get(0).length()));
        for (String s: labelColumn) {
            stringBuffer.append(s).append('\t');
        }
        stringBuffer.append('\n');
        for (String s: labelRow) {
            stringBuffer.append(s);
            stringBuffer.append('\n');
        }
        return stringBuffer.toString();
    }
}

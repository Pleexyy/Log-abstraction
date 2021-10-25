package model;

import java.util.Arrays;

public class Matrix {
    private Event[][] matrix;
    private int height;
    private int width;

    public Matrix(int height, int width) {
        this(height, width, null);
    }

    public Matrix(int height, int width, Event fill) {
        this(new Event[height][width]);
        for(Event[] d : matrix) {
            Arrays.fill(d, fill);
        }
    }

    public Matrix(Event[][] matrix) {
        this.matrix = matrix;
        this.height = matrix.length;
        this.width = (height > 0) ? matrix[0].length : 0;
    }

    public Matrix(Event[] vector) {
        this.matrix = new Event[vector.length][1];
        this.height = vector.length;
        this.width = 1;
        for(int i = 0; i < vector.length; i++)
            this.matrix[i][0] = vector[i];
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public Event get(int i, int j) {
        return matrix[i][j];
    }

    public void set(int i, int j, Event val) {
        matrix[i][j] = val;
    }

    public Matrix extend(int rows, int cols) {
        return extend(rows, cols, null);
    }

    public Matrix extend(int rows, int cols, Event fill) {
        Matrix result = new Matrix(height()+rows, width()+cols);
        for(int i = 0; i < result.height(); i++)
            for(int j = 0; j < result.width(); j++)
                result.set(i, j,
                        (i < height() && j < width()) ? get(i,j) : fill);
        return result;
    }

    public Matrix mergeRight(Matrix other) {
        Matrix result = new Matrix(height(), width() + other.width());
        for(int i = 0; i < height(); i++) {
            for(int j = 0; j < width(); j++)
                result.set(i,j,get(i,j));
            for(int j = width(), k = 0; j < result.width(); j++, k++ )
                result.set(i, j, other.get(i,k));
        }
        return result;
    }

    public Matrix mergeDown(Matrix other) {
        Matrix result = new Matrix(height() + other.height(), width());
        for(int j = 0; j < width(); j++) {
            for(int i = 0; i < height(); i++)
                result.set(i,j,get(i,j));
            for(int i = height(), k = 0; i < result.height(); i++, k++)
                result.set(i,j,other.get(k,j));
        }
        return result;
    }

    public Matrix col(int j) {
        return keepCols(new int[] {j});
    }

    public Matrix firstNCols(int n) {
        int[] cols = new int[n];
        for(int i = 0; i < cols.length; i++)
            cols[i] = i;
        return keepCols(cols);
    }

    public Matrix keepCols(int[] cols) {
        Matrix result = new Matrix(height(), cols.length);
        for(int j = 0; j < cols.length; j++)
            for(int i = 0; i < height(); i++)
                result.set(i, j, get(i,cols[j]));
        return result;
    }

    public Matrix row(int i) {
        return keepRows(new int[] {i});
    }

    public Matrix firstNRows(int n) {
        int[] rows = new int[n];
        for(int i = 0; i < rows.length; i++)
            rows[i] = i;
        return keepRows(rows);
    }

    public Matrix keepRows(int[] rows) {
        Matrix result = new Matrix(rows.length, width());
        for(int i = 0; i < rows.length; i++)
            for(int j = 0; j < width(); j++)
                result.set(i, j, get(rows[i],j));
        return result;
    }

    public Matrix subMatrix(int rows, int cols) {
        return subMatrix(rows,cols,0,0);
    }

    public Matrix subMatrix(int rows, int cols, int i, int j) {
        int[] rowsToKeep = new int[rows];
        int[] colsToKeep = new int[cols];//TODO
        for(int k = i, l = 0; k < i+rows; k++, l++)
            rowsToKeep[l] = k;
        for(int k = j, l = 0; k < j+cols; k++, l++)
            colsToKeep[l] = k;
        return keepRows(rowsToKeep).keepCols(colsToKeep);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < height(); i++) {
            sb.append("[");
            for(int j = 0; j < width(); j++) {
                sb.append(get(i,j));
                if(j != width()-1) sb.append(", ");
            }
            sb.append("]\n");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
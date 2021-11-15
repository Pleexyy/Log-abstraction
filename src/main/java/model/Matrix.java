package model;

import java.util.Arrays;

public class Matrix {
    private final Event[][] matrix;
    private final int height;
    private final int width;

    public Matrix(int height, int width) {
        this(height, width, null);
    }

    public Matrix(int height, int width, Event fill) {
        this(new Event[height][width]);
        for (Event[] d : matrix) {
            Arrays.fill(d, fill);
        }
    }

    public Matrix(Event[][] matrix) {
        this.matrix = matrix;
        this.height = matrix.length;
        this.width = (height > 0) ? matrix[0].length : 0;
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
        Matrix result = new Matrix(height() + rows, width() + cols);
        for (int i = 0; i < result.height(); i++)
            for (int j = 0; j < result.width(); j++)
                result.set(i, j,
                        (i < height() && j < width()) ? get(i, j) : fill);
        return result;
    }

    public Matrix col(int j) {
        return keepCols(new int[]{j});
    }

    public Matrix firstNCols(int n) {
        int[] cols = new int[n];
        for (int i = 0; i < cols.length; i++)
            cols[i] = i;
        return keepCols(cols);
    }

    public Matrix keepCols(int[] cols) {
        Matrix result = new Matrix(height(), cols.length);
        for (int j = 0; j < cols.length; j++)
            for (int i = 0; i < height(); i++)
                result.set(i, j, get(i, cols[j]));
        return result;
    }

    public Matrix row(int i) {
        return keepRows(new int[]{i});
    }

    public Matrix firstNRows(int n) {
        int[] rows = new int[n];
        for (int i = 0; i < rows.length; i++)
            rows[i] = i;
        return keepRows(rows);
    }

    public Matrix keepRows(int[] rows) {
        Matrix result = new Matrix(rows.length, width());
        for (int i = 0; i < rows.length; i++)
            for (int j = 0; j < width(); j++)
                result.set(i, j, get(rows[i], j));
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height(); i++) {
            sb.append("[");
            for (int j = 0; j < width(); j++) {
                sb.append(get(i, j));
                if (j != width() - 1) sb.append(", ");
            }
            sb.append("]\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public Matrix removeColumnNb(int num) {
        Matrix result = new Matrix(height(), width() - 1);
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < num; ++j) {
                result.set(i, j, get(i, j));
            }
            for (int j = num + 1; j < width; ++j) {
                result.set(i, j - 1, get(i, j));
            }
        }
        return result;
    }

}
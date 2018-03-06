import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;

public class Matrix {

    private int[][] matr;

    public static Matrix identity(int n) {
        Matrix temp = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            temp.setVal(1, i, i);
        }
        return temp;
    }

    public Matrix(int n) { matr = new int[n][n]; }

    public Matrix(int r, int c) {
        matr = new int[r][c];
    }

    public int getR() {
        return matr.length;
    }

    public int getC() {
        return matr[0].length;
    }

    public boolean setVal(int val, int r, int c) {
        if (r < getR() && c < getC()) {
            matr[r][c] = val;
            return true;
        }
        else return false;
    }

    public Matrix add(Matrix m) throws UnsupportedOperationException {
        if (getR() == m.getR() && getC() == m.getC()) {
            int r = getR();
            int c = getC();
            Matrix temp = new Matrix(r, c);
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    temp.setVal(matr[i][j]+ m.matr[i][j], i, j);
                }
            }
            return temp;
        }
        else throw new UnsupportedOperationException("Matrices must have an equal number of rows and columns in order to add them.");
    }

    public Matrix multiply(Matrix m) throws UnsupportedOperationException {
        if (getC() == m.getR()) {
            Matrix temp = new Matrix(getR(), m.getC());
            for (int i = 0; i < getR(); i++) {
                for (int j = 0; j < m.getC(); j++) {
                    for (int k = 0; k < getC(); k++) {
                        temp.setVal(matr[i][k] * m.matr[k][j], i, j);
                    }
                }
            }
            return temp;
        }
        else throw new UnsupportedOperationException("Matrix A must have the same numer of columns as matrix B has rows!");
    }

    public String toString() {
        String out = "";
        for (int i = 0; i < getR(); i++) {
            for (int j = 0; j < getC(); j++) {
                out += matr[i][j] + " ";
            }
            out += "\n";
        }
        return out;
    }

}

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class Matrix {

    private double[][] matr;

    public static Matrix identity(int n) {
        Matrix temp = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            temp.setVal(1, i, i);
        }
        return temp;
    }

    public static Matrix compat(int r, int c) {
        Matrix temp = new Matrix(r, c);
        for (double[] d : temp.matr)
            Arrays.fill(d, 1);
        return temp;
    }

    public Matrix(int n) { matr = new double[n][n]; }

    public Matrix(int r, int c) {
        matr = new double[r][c];
    }

    public Matrix(double[][] matr) { this.matr = matr; }

    public int getR() {
        return matr.length;
    }

    public int getC() {
        return matr[0].length;
    }

    public boolean setVal(double val, int r, int c) {
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

    public Matrix multiply(double d) {
        Matrix ret = clone();
        for (int i = 0; i < getR(); i++) {
            for (int j = 0; j < getC(); j++) {
                ret.setVal(matr[i][j]*d, i, j);
            }
        }
        return ret;
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

    public double det() {
        if (isSquare()) {
            int n = getR();
            if (n == 1) {
                return matr[0][0];
            }
            else if (n == 2) {
                return matr[0][0]*matr[1][1] - matr[1][0]*matr[0][1];
            }
            else {
                double ret = 0;
                for (int i = 0; i < n; i++) {
                    Matrix temp = new Matrix(n-1);


                    for (int j = 1; j < n; j++) {
                        for (int k = 0; k < n; k++) {
                            if (k < i)
                                temp.setVal(matr[j][k], j-1, k);
                            else if (k > i)
                                temp.setVal(matr[j][k], j-1,k-1);
                        }
                    }

                    ret += matr[0][i]*Math.pow(-1, (double)i)*temp.det();

                }
                return ret;
            }
        }
        else return Double.NaN;
    }

    public double trace() throws UnsupportedOperationException {
        return MatrixUtils.createRealMatrix(matr).getTrace();
    }

    public Matrix inverse() throws UnsupportedOperationException {
        if (isSquare()) {
            double[][] newMatr = MatrixUtils.inverse(MatrixUtils.createRealMatrix(matr)).getData();
            Matrix ret = new Matrix(getR(), getC());
            ret.matr = newMatr;
            return ret;
        }
        else throw new UnsupportedOperationException("Non-square matrices cannot be inverted.");
    }

    public boolean isSquare() {
        return getR() == getC();
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

    public Matrix clone() {
        Matrix ret = new Matrix(getR(), getC());
        ret.matr = matr;
        return ret;
    }

}

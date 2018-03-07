import java.lang.reflect.Array;
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

    public Matrix(int n) { matr = new double[n][n]; }

    public Matrix(int r, int c) {
        matr = new double[r][c];
    }

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
        if (getR() == getC()) {
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
                    Matrix temp = new Matrix(n-1, n-1);


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
        if (getR() == getC()) {
            double ret = 0;
            int n = getR();
            for (int i = 0; i < n; i++) {
                ret += matr[i][i];
            }
            return ret;
        }
        else throw new UnsupportedOperationException("Trace cannot be applied to non-square matrices.");
    }

    public Matrix inverse() throws UnsupportedOperationException {
        if (getR() == getC() && det() != 0) {
            int n = getR();
            Matrix ret = new Matrix(n, n);
            if (n == 1) {
                ret.setVal(1.0/matr[0][0], 0, 0);
            }
            else if (n == 2) {
                ret = identity(2).multiply(trace()).add(multiply(-1)).multiply(1.0/det());
            }
            else if (n == 3) {
                ret = identity(3).multiply((trace()*trace()-multiply(this).trace())/2.0).add(multiply(-trace())).add(multiply(this)).multiply(1.0/det());
            }
            else if (n == 4) {

            }
            return ret;
        }
        else throw new UnsupportedOperationException("Non-square matrices cannot be inverted.");
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

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;

public class Matrix {

    private int[][] matr;


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

}

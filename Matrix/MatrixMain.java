import java.util.Random;

public class MatrixMain {

    public static void main(String...args) {
        Random r = new Random();
        int n = r.nextInt(5)+1;
        Matrix m = new Matrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int set = r.nextInt(3)-1;
                m.setVal(set, i, j);
            }
        }
        System.out.println(m);
        System.out.println(m.det());
    }
}

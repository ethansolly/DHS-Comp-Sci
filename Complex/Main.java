import java.applet.Applet;
import java.awt.*;
import java.util.function.Function;

public class Main extends Applet {

    boolean useT = true;
    double t;

    private final ComplexFunction function = new ComplexFunction(c -> ComplexFunction.INVERSE.contour());
    private static final Color[] palette = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE};

    double x1 = -1;
    double x2 = 1;
    double y1 = -1;
    double y2 = 1;

    double t1 = -10;
    double t2 = 10;
    double tInc = 0.01;

    public void paint(Graphics g) {
        if (useT) {
            for (t = t1; t <= t2; t += tInc) {
                graph(g);
            }
        }
        else {
            graph(g);
        }
        System.out.println("Done!");
    }

    public void graph(Graphics g) {
        for (int i = 0; i < getWidth(); i += 1) {
            for (int j = 0; j < getHeight(); j += 1) {
                double x = x2 * ((double) i / getWidth()) + x1 * (1.0 - (double) i / getWidth());
                double y = y2 * ((double) j / getHeight()) + y1 * (1.0 - (double) j / getHeight());
                Complex c = function.apply(new Complex(x, -y));
                System.out.println("( " + x + ", " + y + ") => " + "( " + c.a + ", " + c.b + ")");
                g.setColor(c.getColor());
                g.drawRect(i, j, 1, 1);
            }
        }
    }
}

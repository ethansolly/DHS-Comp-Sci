import javax.tools.Tool;
import java.applet.Applet;
import java.awt.*;
import java.util.function.Function;

public class Main extends Applet {

    private final Function<Complex, Complex> function = Complex::cos;
    private static final Color[] palette = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE};

    public void init() {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public void paint(Graphics g) {
        for(int i = 0; i < getWidth(); i+=1) {
            for (int j = 0; j < getHeight(); j+=1) {
                double x = 2*((double)i/getWidth())-1;
                double y = 2*((double)j/getHeight())-1;
                Complex c = function.apply(new Complex(x, y));
                //System.out.println("( " + x + ", " + y + ") => " + "( " + c.a + ", " + c.b + ")");
                g.setColor(c.getColor());
                g.drawRect(i, j, 1, 1);
            }
        }
    }
}

package memes;

import java.awt.*;
import java.math.BigDecimal;
import java.util.function.Function;

public class Utilz {
    public static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    
    public static void drawFunction(Graphics g, Function<Double, Double> function, Dimension scr, Rectangle functSpace) {

        int scrWidth = scr.width;
        int scrHeight = scr.height;
        double xLeft = functSpace.x;
        double xRight = xLeft + functSpace.width;
        double yBottom = functSpace.y;
        double yTop = yBottom + functSpace.height;


        //Here , (i,j) represents the pixel, (x,y) is the point of the function
        for (int i = 0; i < scrWidth; i++) {
            //Draw function
            double x = (xRight - xLeft) * ((double) i) / scrWidth + xLeft;
            double y = function.apply(x);
            int j = scrHeight - (int) (scrHeight * (y - yBottom) / (yTop - yBottom));
            g.drawRect(i, j, 1, 1);
        }
    }

}

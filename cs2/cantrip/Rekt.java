package com.ethansolly.cantrip;


import java.applet.Applet;
import java.awt.*;

/**
 * Created by 705937 on 4/5/2017.
 */
public class Rekt extends Applet {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();
    int height = (int) screenSize.getHeight();

    public void init() {
        setSize(width, height);
    }

    public void paint(Graphics g) {

        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();

        while (true) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (isSierpinskiCarpetPixelFilled(i, j))
                        g.drawRect(i, j, 1, 1);
                }
            }
        }
    }

    /**
     * Decides if a point at a specific location is filled or not.  This works by iteration first checking if
     * the pixel is unfilled in successively larger squares or cannot be in the center of any larger square.
     * @param x is the x coordinate of the point being checked with zero being the first pixel
     * @param y is the y coordinate of the point being checked with zero being the first pixel
     * @return 1 if it is to be filled or 0 if it is open
     */
    private boolean isSierpinskiCarpetPixelFilled(int x, int y)
    {
        while(x > 0 || y > 0) {
            if(x%3==1 && y%3==1) //checks if the pixel is in the center for the current square level
                return true;
            x /= 3; //x and y are decremented to check the next larger square level
            y /= 3;
        }
        return false;
    }
}

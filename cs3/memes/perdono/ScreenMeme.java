package memes.perdono;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScreenMeme extends JFrame {

    private static Dimension screen = Utilz.screen;
    private GraphicsOperation go;

    public ScreenMeme(GraphicsOperation go) {
        setSize(screen);
        setMinimumSize(screen);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setVisible(true);

        this.go = go;

    }

    public void paint(Graphics g) {
        go.draw(g);
    }

    private static final int SETTING = 1;

    public static void main(String...args) {
        /*
        ScreenMeme sm = new ScreenMeme(g -> {
            double t = 0;
            double inc = 0.01;
            boolean forward = true;
            while (true) {
                if (t == 1) forward = false;
                if (forward)
                    t += inc;
                else
                    t -= inc;
                Color[][] colors = new Color[screen.width][screen.height];
                for (int i = 0; i < screen.width; i++) {
                    for (int j = 0; j < screen.height; j++) {
                        double meme = meme(SETTING, i, j, t);
                        Color c = new Color((int) meme % 0xffffff);
                        colors[i][j] = c;
                    }
                }
                for (int i = 0; i < screen.width; i++) {
                    for (int j = 0; j < screen.height; j++) {
                        g.setColor(colors[i][j]);
                        g.drawRect(i, j, 1, 1);
                    }
                }
                //System.out.println(t);
            }
        });
        */


        ScreenMeme smee = new ScreenMeme(g -> {

            /*
            for (double xInit = 0; xInit <= 1; xInit+=0.01) {
                for (double rp = 0.0; rp < screen.width; rp++) {
                    Map<Double, Integer> count;

                    //Run first values
                    double xVal = xInit;
                    double rVal = 4 * rp / screen.width;
                    ArrayList<Double> xVals = new ArrayList<>();
                    int iter = 0;
                    int max = 1000;
                    while (iter < max) {
                        xVal = rVal * xVal * (1 - xVal); //Apply map
                        xVals.add(xVal);
                        iter++;
                    }

                    count = count(xVals);

                    //System.out.println(count);
                    for (Double d : count.keySet()) {
                        int dp = (int) (d * screen.height);
                        //g.setColor(new Color((float)(xInit), (float)(rVal/4), (float)(Math.sqrt(1.0-xInit*xInit-rVal*rVal/16)), (float) (2 * Math.atan(count.get(d)) / Math.PI)));
                        g.setColor(new Color(0, 0, 0, (float) (2 * Math.atan(count.get(d)) / Math.PI)));
                        g.drawRect((int) rp, dp, 1, 1);
                    }
                }
            }*/



            g.setColor(Color.WHITE);
            g.fillRect(0, 0, screen.width, screen.height);

            final double[] BIFURC = {0, 0, 3, 3.449, 3.54409, 3.5644};
            final double END = 4;
            final double MINR = BIFURC[5];
            final double MAXR = 4;

            final int SPLITS = 10;

            for (double rp = 0.0; rp < screen.width; rp++) {
                Map<Double, Integer> count;
                ArrayList<Double> xVals = new ArrayList<>();

                double rVal = MINR + (MAXR-MINR) * rp / screen.width;

                for (double xInit = 0; xInit < 1; xInit+=0.01) {

                    //Run first values
                    double xVal = xInit;
                    int iter = 0;
                    int max = 50;
                    while (iter < max) {
                        xVal = rVal * xVal * (1 - xVal); //Apply map
                        xVals.add(xVal);
                        iter++;
                    }
                }

                count = count(xVals);

                final int ERROR = 1;
                final int CLARITY = 10;
                //System.out.println(count);
                for (Double d : count.keySet()) {
                    int oof = count.get(d);
                    if (oof > ERROR) {
                        int dp = (int) (d * screen.height);
                        //g.setColor(new Color((float)(xInit), (float)(rVal/4), (float)(Math.sqrt(1.0-xInit*xInit-rVal*rVal/16)), (float) (2 * Math.atan(count.get(d)) / Math.PI)));
                        g.setColor(new Color(0, 0, 0, (float) (2/(1 + Math.exp(-(double)oof/CLARITY)) - 1)));
                        g.drawRect((int) rp, dp, 1, 1);
                    }
                }
            }

        });


    }

    private static Map<Double, Integer> count(ArrayList<Double> list) {
        Map<Double, Integer> temp = new HashMap<>();
        for (Double d : list) {
            if (temp.containsKey(d))
                temp.replace(d, temp.get(d)+1);
            else
                temp.put(d, 1);
        }
        return temp;
    }


    private static double meme(int n, double i, double j, double t) {
        double x;
        double y;
        switch (n) {
            case 0:
                x = 1 / (Math.cos(i * j)) * (t);
                y = 1 / (Math.sin(i * j)) * (1 - t);
                return x + y;
            case 1:
                x = 1/(Math.tan(i * j)) * (t);
                y = Math.tan(i * j) * (1-t);
                return x + y;
            case 2:
                double k = 10;
                x = Math.sqrt(i*j*Math.cos(i/k) + i*Math.sin(j/k))*(t);
                y = Math.sqrt(i*Math.cos(j/k) + i*j*Math.sin(i/k))*(1-t);
                return x + y;
            case 3:
                x = (1/Math.atan2(j*j, i*i))*(t);
                y = (1/Math.atan2(i*i, j*j))*(1-t);
                return x + y;
            case 4:

        }
        return 0;
    }
}

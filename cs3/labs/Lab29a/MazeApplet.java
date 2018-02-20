package labs.Lab29a;

import memes.perdono.Utilz;

import java.applet.Applet;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by ethan.solly on 10/24/2017.
 */
public class MazeApplet extends Applet {

    private int len;
    private Maze m;

    public void init() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter side length ->");
        len = sc.nextInt();
        System.out.println();
        m = new Maze(len);
        m.primAlg();
        m.solve();
        setSize(Utilz.screen);
        setVisible(true);
    }

    public void paint(Graphics g) {

        Color trail = Color.RED;

        int totWidth = getWidth();
        int totHeight = getHeight();

        //Draw grid
        int widthInterval = totWidth / len;
        int heightInterval = totHeight / len;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (m.getMaze()[i][j] == '#') {
                    g.setColor(Color.BLACK);
                    g.fillRect(widthInterval * j, heightInterval * i, widthInterval, heightInterval);
                }
                else if (m.getMaze()[i][j] == '*') {
                    g.setColor(trail);
                    g.fillRect(widthInterval*j, heightInterval * i, widthInterval, heightInterval);
                }
                else
                    g.setColor(Color.WHITE);
            }
        }
    }
}

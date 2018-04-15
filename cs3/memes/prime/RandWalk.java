package memes.prime;

import memes.perdono.Utilz;

import javax.swing.*;
import java.awt.*;

public class RandWalk extends JWindow {
    private int nextX;
    private int nextY;

    public void init() {
        setSize(Utilz.screen);
        nextX = Utilz.screen.width/2;
        nextY = Utilz.screen.height/2;
    }

    public void paint(Graphics g) {
        while (true) {
            walk(g);
        }
    }

    public void walk(Graphics g) {
        int currentX = nextX;
        int currentY = nextY;

        int rand = (int)(Math.random()*8); //[0...7]
        switch (rand) {
            case 0:
                nextX++;
                break;
            case 1:
                nextX++;
                nextY--;
                break;
            case 2:
                nextY--;
                break;
            case 3:
                nextX--;
                nextY--;
                break;
            case 4:
                nextX--;
                break;
            case 5:
                nextX--;
                nextY++;
                break;
            case 6:
                nextY++;
                break;
            case 7:
                nextX++;
                nextY++;
                break;
        }

        g.drawLine(currentX, currentY, nextX, nextY);
    }
}

package automata;

import javax.swing.*;
import java.awt.*;

public class ColorAutomata extends JFrame {
    private Color[][] board;

    public static void main(String...args) {
        ColorAutomata ca = new ColorAutomata();
    }

    public ColorAutomata() {
        super("Color Automata");
        setSize(300,300);

        board = new Color[getWidth()][getHeight()];

        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                int R = (int)(Math.random()*256);
                int G = (int)(Math.random()*256);
                int B = (int)(Math.random()*256);
                board[i][j] = new Color(R, G, B);
            }
        }

        setVisible(true);
    }

    public void paint(Graphics g) {
        while (true) {

            Color[][] tempBoard = new Color[getWidth()][getHeight()];

            for (int i = 1; i < getWidth()-1; i++) {
                for (int j = 1; j < getHeight()-1; j++) {
                    double R, G, B;
                    Color c = board[i][j];
                    R = c.getRed();
                    G = c.getGreen();
                    B = c.getBlue();

                    //System.out.println(i + ":" + j);

                    //0
                    c = board[i+1][j];
                    if (c != null) {
                        R += c.getRed();
                        G += c.getGreen();
                        B += c.getBlue();
                    }

                    //1
                    c = board[i+1][j-1];
                    if (c != null) {
                        R += c.getRed();
                        G += c.getGreen();
                        B += c.getBlue();
                    }


                    //2
                    c = board[i][j-1];
                    if (c != null) {
                        R += c.getRed();
                        G += c.getGreen();
                        B += c.getBlue();
                    }

                    //3
                    c = board[i-1][j-1];
                    if (c != null) {
                        R += c.getRed();
                        G += c.getGreen();
                        B += c.getBlue();
                    }

                    //4
                    c = board[i-1][j];
                    if (c != null) {
                        R += c.getRed();
                        G += c.getGreen();
                        B += c.getBlue();
                    }

                    //5
                    c = board[i - 1][j + 1];
                    if (c != null) {
                        R += c.getRed();
                        G += c.getGreen();
                        B += c.getBlue();
                    }

                    //6
                    c = board[i][j+1];
                    if (c != null) {
                        R += c.getRed();
                        G += c.getGreen();
                        B += c.getBlue();
                    }

                    //7
                    c = board[i+1][j+1];
                    if (c != null) {
                        R += c.getRed();
                        G += c.getGreen();
                        B += c.getBlue();
                    }

                    R /= 9;
                    G /= 9;
                    B /= 9;

                    //Do stuff with avg value here

                    c = board[i][j];

                    double tempR = G-B;
                    double tempG = R*(1-B)/G-G;
                    double tempB = R-B;

                    R = posMod(tempR, 256);
                    G = posMod(tempG, 256);
                    B = posMod(tempB, 256);

                    tempBoard[i][j] = new Color((int)R, (int)G, (int)B);

                }
            }

            for (int i = 0; i < getWidth(); i++) {
                for (int j = 0; j < getHeight(); j++) {
                    board[i][j] = tempBoard[i][j];
                    g.setColor(board[i][j]);
                    g.drawRect(i, j, 0, 0);
                }
            }
        }
    }

    public static double posMod(double x, double a) {
        if (x >= 0) {
            return x % a;
        }
        else {
            return (-x) % a;
        }
    }
}

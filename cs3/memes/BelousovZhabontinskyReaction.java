package memes;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class BelousovZhabontinskyReaction extends JFrame {

    private double alpha, beta, gamma;
    private double[][][] num;

    public static void main(String...args) {
        BelousovZhabontinskyReaction bzr = new BelousovZhabontinskyReaction(0.42, 0.69, 1.27);
    }

    public BelousovZhabontinskyReaction(double alpha, double beta, double gamma) {
        super("Belousov-Zhabotinsky Reaction Simulator");
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
        setSize(300, 300);
        setVisible(true);
    }

    public void paint(Graphics g) {

        num = new double[getWidth()][getHeight()][4];
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                Arrays.fill(num[i][j], 0);
                int put = (int)(4*Math.random());
                num[i][j][put] = 1;
                //0 : nothing
                //1 : A
                //2 : B
                //3 : C
            }
        }

        while(true) {

            double[][][] tNum = new double[getWidth()][getHeight()][4];

            for (int i = 1; i < getWidth()-1; i++) {
                for (int j = 1; j < getHeight()-1; j++) {
                    /*  4 3 2
                     *  5 0 1
                     *  6 7 8
                     */
                    double sumA = (num[i][j][1] + num[i][j+1][1] + num[i-1][j+1][1] + num[i-1][j][1] + num[i-1][j-1][1] + num[i][j-1][1] + num[i+1][j-1][1] + num[i+1][j][1] + num[i+1][j+1][1])/9;
                    double sumB = (num[i][j][2] + num[i][j+1][2] + num[i-1][j+1][2] + num[i-1][j][2] + num[i-1][j-1][2] + num[i][j-1][2] + num[i+1][j-1][2] + num[i+1][j][2] + num[i+1][j+1][2])/9;
                    double sumC = (num[i][j][3] + num[i][j+1][3] + num[i-1][j+1][3] + num[i-1][j][3] + num[i-1][j-1][3] + num[i][j-1][3] + num[i+1][j-1][3] + num[i+1][j][3] + num[i+1][j+1][3])/9;

                    tNum[i][j][1] = (sumA + sumA*(alpha*sumB - gamma*sumC));
                    tNum[i][j][2] = (sumB + sumB*(beta*sumC - alpha*sumA));
                    tNum[i][j][3] = (sumC + sumC*(gamma*sumA - beta*sumB));

                    tNum[i][j][1] = Math.max(0, Math.min(1,tNum[i][j][1]));
                    tNum[i][j][2] = Math.max(0, Math.min(1,tNum[i][j][2]));
                    tNum[i][j][3] = Math.max(0, Math.min(1,tNum[i][j][3]));
                }
            }

            for (int i = 0; i < getWidth(); i++) {
                for (int j = 0; j < getHeight(); j++) {
                    for (int k = 0; k < 4; k++) {
                        num[i][j][k] = tNum[i][j][k];
                    }

                    g.setColor(new Color((float)(num[i][j][1]),(float)(num[i][j][2]),(float)(num[i][j][3])));
                    g.drawRect(i,j,1,1);

                }
            }



        }
    }

}

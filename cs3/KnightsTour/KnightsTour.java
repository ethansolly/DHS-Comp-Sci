package KnightsTour;

import javafx.application.Application;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class KnightsTour extends Applet {

    /**
     * MUCH Credit to
     * https://github.com/douglassquirrel/warnsdorff/blob/master/5_Squirrel96.pdf?raw=true
     * for helping me break ties.
     */


    private int moveCount = 0;
    private int width;
    private int height;
    private int r;
    private int c;
    private static int totWidth;
    private static int totHeight;
    private int[] ordering;
    private boolean stop = false;

    public KnightsTour() {
        Scanner sc = new Scanner(System.in);
        /*
        System.out.print("Enter width ->");
        width = sc.nextInt();
        System.out.println();
        System.out.print("Enter height ->");
        height = sc.nextInt();
        System.out.println();
        */

        System.out.print("Enter side length ->");
        width = height = sc.nextInt();
        System.out.println();

        System.out.print("Enter row ->");
        r = sc.nextInt();
        System.out.println();
        System.out.print("Enter column ->");
        c = sc.nextInt();
        System.out.println();

        setSize(totWidth, totHeight);
        setVisible(true);
    }

    public void paint(Graphics g) {
        stop = false;
        totWidth = getWidth();
        totHeight = getHeight();
        if (Math.min(width, height) >= 5) {
            System.out.print("Knight's Tour is POSSIBLE");
            if ((width % 2 == 1 && height % 2 == 1)
                    || (width == 1 || width == 2 || width == 4)
                    || (width == 3 && (height == 4 || height == 6 || height == 8)))
                System.out.println(" but can NOT be Closed.");
            else
                System.out.println(" and CAN be Closed.");

            System.out.println();

            //Draw grid
            int widthInterval = totWidth / width;
            int heightInterval = totHeight / height;

            //Vertical lines
            for (int i = 0; i <= width; i++)
                g.drawLine(widthInterval * i, 0, widthInterval * i, totHeight);

            //Horizontal lines
            for (int i = 0; i <= height; i++)
                g.drawLine(0, heightInterval * i, totWidth, heightInterval * i);

            System.out.print("Creating Knight's Graph");
            //knightGraph[r][c]
            int[][] knightGraph = new int[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    // If on top row or bottom row (row 0)
                    if (i == 0 || i == height - 1) {
                        //Column 0
                        if (j == 0 || j == width - 1)
                            knightGraph[i][j] = 2;
                            //Column 1
                        else if (j == 1 || j == width - 2)
                            knightGraph[i][j] = 3;
                            //Other
                        else
                            knightGraph[i][j] = 4;
                    }
                    //Row 1
                    else if (i == 1 || i == height - 2) {
                        //Column 0
                        if (j == 0 || j == width - 1)
                            knightGraph[i][j] = 3;
                            //Column 1
                        else if (j == 1 || j == width - 2)
                            knightGraph[i][j] = 4;
                            //Other
                        else
                            knightGraph[i][j] = 6;
                    }
                    //Other
                    else {
                        //Column 0
                        if (j == 0 || j == width - 1)
                            knightGraph[i][j] = 4;
                            //Column 1
                        else if (j == 1 || j == width - 2)
                            knightGraph[i][j] = 6;
                            //Other
                        else
                            knightGraph[i][j] = 8;
                    }
                }
            }
            //Finished Knight's Graph
            //Now to ~die~
            System.out.println();
            System.out.println("Generating Knight's Tour");
            int currentR = r;
            int currentC = c;
            double initTime = System.currentTimeMillis();
            while (!stop) {

                //Set current location to max: already visited
                knightGraph[currentR][currentC] = Integer.MAX_VALUE;

                /**    2   1
                 *   3       0
                 *       X
                 *   4       7
                 *     5  6
                 */

                //Get move of least effort according to the rules outlined in Squirrel
                int leastEffort = leastEffort(currentR, currentC, knightGraph);
                if (leastEffort != -1) {

                    int currentX = (int) (widthInterval * (currentC + 0.5));
                    int currentY = (int) (heightInterval * (currentR + 0.5));
                    int newX;
                    int newY;
                    int tempR = 0;
                    int tempC = 0;

                    if (0 == leastEffort) {
                        tempC = currentC + 2;
                        tempR = currentR - 1;
                    } else if (1 == leastEffort) {
                        tempC = currentC + 1;
                        tempR = currentR - 2;
                    } else if (2 == leastEffort) {
                        tempC = currentC - 1;
                        tempR = currentR - 2;
                    } else if (3 == leastEffort) {
                        tempC = currentC - 2;
                        tempR = currentR - 1;
                    } else if (4 == leastEffort) {
                        tempC = currentC - 2;
                        tempR = currentR + 1;
                    } else if (5 == leastEffort) {
                        tempC = currentC - 1;
                        tempR = currentR + 2;
                    } else if (6 == leastEffort) {
                        tempC = currentC + 1;
                        tempR = currentR + 2;
                    } else if (7 == leastEffort) {
                        tempC = currentC + 2;
                        tempR = currentR + 1;
                    }


                    newX = (int) (widthInterval * (tempC + 0.5));
                    newY = (int) (heightInterval * (tempR + 0.5));

                    g.drawLine(currentX, currentY, newX, newY);

                    currentR = tempR;
                    currentC = tempC;
                }
            }
            double stopTime = System.currentTimeMillis();
            System.out.println("Time: " + (stopTime - initTime));


        } else {
            System.out.println("Knight's Tour is IMPOSSIBLE");
            System.exit(0);
        }
    }

    //Returns indices of the minimum values in array list
    public ArrayList<Integer> mindices(int... list) {
        ArrayList<Integer> ret = new ArrayList<>();
        int min = Arrays.stream(list).min().getAsInt();
        if (min == 9) {
            System.out.println("Knight's Tour Complete!");
            stop = true;
        }
        //Add all points equal to the minimum
        for (int i = 0; i < list.length; i++) {
            if (list[i] == min)
                ret.add(i);
        }
        return ret;
    }

    //finds move of least effort
    //The pinnacle of my existence
    public int leastEffort(int currentR, int currentC, int[][] knightGraph) {
        int[] move = new int[8];
        Arrays.fill(move, 9);

        //Get moves
        if (currentR >= 1 && currentC <= width - 2 - 1)
            move[0] = knightGraph[currentR - 1][currentC + 2]--;
        if (currentR >= 2 && currentC <= width - 1 - 1)
            move[1] = knightGraph[currentR - 2][currentC + 1]--;

        if (currentR >= 2 && currentC >= 1)
            move[2] = knightGraph[currentR - 2][currentC - 1]--;
        if (currentR >= 1 && currentC >= 2)
            move[3] = knightGraph[currentR - 1][currentC - 2]--;

        if (currentR <= height - 1 - 1 && currentC >= 2)
            move[4] = knightGraph[currentR + 1][currentC - 2]--;
        if (currentR <= height - 2 - 1 && currentC >= 1)
            move[5] = knightGraph[currentR + 2][currentC - 1]--;

        if (currentR <= height - 2 - 1 && currentC <= width - 1 - 1)
            move[6] = knightGraph[currentR + 2][currentC + 1]--;
        if (currentR <= height - 1 - 1 && currentC <= width - 2 - 1)
            move[7] = knightGraph[currentR + 1][currentC + 2]--;

        //Compare moves
        ArrayList<Integer> ties = mindices(move);
        if (!stop) {
            if (ties.size() == 1)
                return ties.get(0);
            else {
                //We have multiple minimums.
                //Set ordering! Cry!
                //#InformationHiding
                int m = Math.min(height, width);
                switch (m % 8) {
                    /** To convert from Squirrel Notation to angular:
                     *  1 -> 1
                     *  2 -> 0
                     *  3 -> 7
                     *  4 -> 6
                     *  5 -> 5
                     *  6 -> 4
                     *  7 -> 3
                     *  8 -> 2
                     *
                     *  Refer to this for the orderings and why they work:
                     *  https://github.com/douglassquirrel/warnsdorff/blob/master/5_Squirrel96.pdf?raw=true
                     *
                     *  A // after an ordering represents one in angular notation.
                     */
                    case 0:
                        if (currentC == c && currentR == r)
                            ordering = new int[]{7, 6, 0, 4, 1, 5, 3, 2}; //
                        else if (currentC == m - 1 - 1 && currentR == m - 2 - 1)
                            ordering = new int[]{2, 3, 4, 6, 0, 1, 7, 5}; //
                        else if (currentC == 2 - 1 && currentR == 2 - 1)
                            ordering = new int[]{5, 1, 2, 4, 3, 7, 6, 0}; //
                        else if (currentC == m - 8 - 1 && currentR == 1 - 1)
                            ordering = new int[]{5, 1, 7, 6, 0, 4, 3, 2}; //
                        else if (currentC == 7 - 1 && currentR == m - 3 - 1)
                            ordering = new int[]{0, 1, 6, 7, 5, 4, 3, 2}; //
                        break;
                    case 1:
                        if (currentC == c && currentR == r)
                            ordering = new int[]{7, 6, 0, 4, 1, 5, 3, 2}; //
                        else if (currentC == m - 1 - 1 && currentR == m - 2 - 1)
                            ordering = new int[]{2, 3, 4, 6, 0, 1, 7, 5}; //
                        else if (currentC == 2 - 1 && currentR == 2 - 1)
                            ordering = new int[]{5, 1, 7, 0, 6, 4, 3, 2}; //
                        else if (currentC == m - 6 - 1 && currentR == (m - 1) / 2 + 5 - 1)
                            ordering = new int[]{7, 0, 6, 2, 1, 3, 4, 5}; //
                        break;
                    case 2:
                        if (currentC == c && currentR == r)
                            ordering = new int[]{7, 6, 0, 4, 1, 5, 3, 2}; //
                        else if (currentC == 6 - 1 && currentR == 1 - 1)
                            ordering = new int[]{2, 3, 4, 6, 0, 1, 7, 5}; //
                        else if (currentC == 3 - 1 && currentR == 1 - 1)
                            ordering = new int[]{5, 6, 1, 7, 0, 4, 3, 2}; //
                        else if (currentC == m - 15 - 1 && currentR == 4 - 1)
                            ordering = new int[]{5, 0, 6, 7, 1, 4, 3, 2}; //
                        else if (currentC == 10 - 1 && currentR == m - 2 - 1)
                            ordering = new int[]{2, 5, 4, 6, 3, 1, 0, 7}; //
                        else if (currentC == 5 - 1 && currentR == m / 2 - 3 - 1)
                            ordering = new int[]{1, 5, 3, 6, 4, 2, 0, 7};
                        break;
                    case 3:
                        if (currentC == c && currentR == r)
                            ordering = new int[]{7, 6, 4, 0, 5, 3, 1, 2}; //
                        else if (currentC == m - 1 - 1 && currentR == m - 2 - 1)
                            ordering = new int[]{6, 0, 4, 2, 1, 7, 5, 3}; //
                        else if (currentC == m - 6 - 1 && currentR == m - 1)
                            ordering = new int[]{2, 4, 5, 1, 0, 7, 6, 3}; //
                        else if (currentC == 2 - 1 && currentR == 5 - 1)
                            ordering = new int[]{5, 1, 2, 4, 3, 7, 6, 0}; //
                        else if (currentC == m - 10 - 1 && currentR == 3 - 1)
                            ordering = new int[]{4, 1, 2, 0, 5, 6, 7, 3}; //
                        else if (currentC == (m - 1) / 2 + 1 - 1 && currentR == m - 2 - 1)
                            ordering = new int[]{3, 1, 4, 6, 0, 5, 7, 2}; //
                        break;
                    case 4:
                        if (currentC == c && currentR == r)
                            ordering = new int[]{7, 6, 0, 4, 1, 5, 3, 2}; //
                        else if (currentC == m - 1 - 1 && currentR == m - 2 - 1)
                            ordering = new int[]{2, 3, 4, 6, 0, 1, 7, 5}; //
                        else if (currentC == 2 - 1 && currentR == 2 - 1)
                            ordering = new int[]{5, 1, 2, 4, 3, 7, 6, 0}; //
                        else if (currentC == m - 8 - 1 && currentR == 1 - 1)
                            ordering = new int[]{5, 1, 7, 6, 0, 4, 3, 2}; //
                        else if (currentC == 10 - 1 && currentR == m - 5 - 1)
                            ordering = new int[]{2, 4, 3, 5, 7, 6, 0, 1}; //
                        else if (currentC == 13 - 1 && currentR == m / 2 + 1 - 1)
                            ordering = new int[]{3, 2, 5, 4, 7, 6, 0, 1}; //
                        break;
                    case 5:
                        if (currentC == c && currentR == r)
                            ordering = new int[]{7, 6, 0, 4, 1, 5, 3, 2}; //
                        else if (currentC == m - 1 - 1 && currentR == m - 2 - 1)
                            ordering = new int[]{2, 3, 4, 6, 0, 1, 7, 5}; //
                        else if (currentC == 2 - 1 && currentR == 2 - 1)
                            ordering = new int[]{5, 1, 7, 0, 6, 4, 3, 2}; //
                        else if (currentC == m - 2 - 1 && currentR == ((m % 16 == 5) ? (m - 1) / 2 - 2 - 1 : (m - 1) / 2 - 6 - 1))
                            ordering = new int[]{1, 5, 0, 7, 6, 4, 3, 2};
                        break;
                    case 6:
                        if (currentC == c && currentR == r)
                            ordering = new int[]{7, 6, 0, 4, 1, 5, 3, 2}; //
                        else if (currentC == 6 - 1 && currentR == 1 - 1)
                            ordering = new int[]{2, 3, 4, 6, 0, 1, 7, 5}; //
                        else if (currentC == 3 - 1 && currentR == 1 - 1)
                            ordering = new int[]{5, 6, 1, 7, 0, 4, 3, 2}; //
                        else if (currentC == m - 10 - 1 && currentR == 1 - 1)
                            ordering = new int[]{5, 0, 6, 7, 1, 4, 3, 2}; //
                        else if (currentC == 10 - 1 && currentR == m - 2 - 1)
                            ordering = new int[]{2, 5, 4, 6, 3, 1, 0, 7}; //
                        else if (currentC == 3 - 1 && currentR == m / 2 + 4 - 1)
                            ordering = new int[]{1, 0, 6, 5, 7, 4, 3, 2}; //
                        break;
                    case 7:
                        if (currentC == c && currentR == r)
                            ordering = new int[]{7, 6, 4, 0, 5, 3, 1, 2}; //
                        else if (currentC == m - 1 - 1 && currentR == m - 2 - 1)
                            ordering = new int[]{6, 0, 4, 2, 1, 7, 5, 3}; //
                        else if (currentC == m - 6 - 1 && currentR == m - 1)
                            ordering = new int[]{2, 4, 5, 1, 0, 7, 6, 3}; //
                        else if (currentC == 2 - 1 && currentR == 5 - 1)
                            ordering = new int[]{5, 1, 2, 4, 3, 7, 6, 0}; //
                        else if (currentC == m - 6 - 1 && currentR == 3 - 1)
                            ordering = new int[]{4, 1, 2, 0, 5, 6, 7, 3}; //
                        else if (currentC == (m - 1) / 2 + 1 - 1 && currentR == m - 2 - 1)
                            ordering = new int[]{4, 1, 7, 5, 3, 0, 2, 6}; //
                        break;
                }

                //Go by order until relevant tie is found
                for (int i : ordering) {
                    for (int j : ties) {
                        if (i == j)
                            return i;
                    }
                }
            }
        }
        return -1;
    }
}

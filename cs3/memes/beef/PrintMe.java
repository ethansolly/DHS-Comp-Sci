package memes.beef;

import memes.perdono.Utilz;
import memes.prime.Prime;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class PrintMe implements Printable {

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int i) throws PrinterException {

        if (i > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g = (Graphics2D)graphics;
        g.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        //GRAPHICS
        Point current;
        int num = 0;
        int primeIndex = 0;
        int direction = 0; //0 = right, 1 = left, 2 = down, 3 = down-right...
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;

        current = new Point((int)(pageFormat.getWidth()/2), (int)(pageFormat.getHeight()/2));

        num = 0;
        int n = 0;
        int MAX = 100000;
        while (n < MAX) {
            //Draw point
            double nextX = current.x;
            double nextY = current.y;


            /**
             //Trees!
             nextX += Math.sin(direction);
             nextY += Math.cos(direction);
             */

            //Oooh!
            direction %= 4;
            switch (direction) {
                case 0:
                    nextX++;
                    break;
                case 1:
                    nextY--;
                    break;
                case 2:
                    nextX--;
                    break;
                case 3:
                    nextY++;
                    break;
            }

            /**
             //Epilepsy.exe
             direction %= 4;
             switch (direction) {
             case 0:
             nextX+=primeIndex;
             break;
             case 1:
             nextY-=primeIndex;
             break;
             case 2:
             nextX-=primeIndex;
             break;
             case 3:
             nextY+=primeIndex;
             break;
             }
             */

            /**
             direction %= 8;
             switch (direction) {
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
             */


            Point next = new Point((int) nextX, (int) nextY);
            draw(g, current, next);


            /**
             //Wrap
             if (nextX > getWidth() || nextX < 0)
             nextX %= getWidth();
             if (nextY > getWidth() || nextY < 0)
             nextY %= getWidth();
             */

            current.setLocation(nextX, nextY);
            minX = (int) Math.min(minX, nextX);
            minY = (int) Math.min(minY, nextY);
            maxX = (int) Math.max(maxX, nextX);
            maxY = (int) Math.max(maxY, nextY);


            //Increment num
            num++;
            if (isPrime(num)) {
                direction++;
                primeIndex++;
            }

            double factor = 1000000.0;
            //g.setColor(new Color((int)(Color.BLUE.getRGB()*(1-(num % factor)/factor) + Color.RED.getRGB()*((num % factor)/(factor)))));

        }



        return PAGE_EXISTS;
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
    }

    private static void draw(Graphics g, Point p1, Point p2) {
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
}

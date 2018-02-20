package memes.perdono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by caelan.solly on 10/13/2017.
 */
public class Utilz {

    public static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private static class UtilzMouseListener implements MouseListener {

        public UtilzMouseListener() {

        }

        public void getMouseLoc(MouseEvent e) {
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            System.out.println("(" + x + ", " + y + ")");
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            getMouseLoc(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static void main(String...args) {
        JFrame jf = new JFrame("Utilz");
        UtilzMouseListener uml = new UtilzMouseListener();
        jf.addMouseListener(uml);
        jf.setSize(screen);
        jf.setVisible(true);
    }


}

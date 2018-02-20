package memes.beef;

import memes.perdono.GraphicsOperation;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class PrintOperation implements Printable {

    private GraphicsOperation go;

    public PrintOperation(GraphicsOperation parGo) {
        go = parGo;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int i) throws PrinterException {

        if (i > 0)
            return NO_SUCH_PAGE;

        Graphics2D g = (Graphics2D)graphics;
        g.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        go.draw(g);

        return PAGE_EXISTS;
    }
}

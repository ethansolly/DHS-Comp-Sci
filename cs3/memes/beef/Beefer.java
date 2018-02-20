package memes.beef;

import memes.perdono.GraphicsOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;

public class Beefer {


    public static void main(String...args) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new PrintMe());
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                System.out.println("The job did not print successfully");
                // The job did not successfully
                // complete
            }
        }
    }


}
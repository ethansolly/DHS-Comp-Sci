package com.brisk.usualTwo.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.brisk.usualTwo.resources.Sound;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

/**Checks if the user's active window is a slide
 * @author Lutalica
 *
 */
public class EnumerateWindows {
    private static final int MAX_TITLE_LENGTH = 1024;
    
	/**
	 * @param args Needed for main method
	 * @throws IOException If the Output file doesn't exist
	 * @throws InterruptedException If the wait is interupted
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		String directory = System.getProperty("user.dir");
        char[] buffer = new char[MAX_TITLE_LENGTH * 2];
        String slideNum;
        String currentWindow;
        BufferedWriter writer = null;
        
        try {
        	
        	File outFile = new File(directory + "\\src\\com\\brisk\\usualTwo\\resources\\log.dat");
        	writer = new BufferedWriter(new FileWriter(outFile.getAbsoluteFile()));
	        writer.write("BEGIN LOG");
        	Thread.sleep(10000);
	        if (args.length>0) {
	        	slideNum = ((Integer.parseInt(args[0])<10)? "0" : "") + args[0];
	        }
	        else {
	        	slideNum = JOptionPane.showInputDialog("What is the number of the slide? (Use two digits, i.e 05)");
	        }
	        int time = 1;
	        while (true) {
		        HWND hwnd = User32.INSTANCE.GetForegroundWindow();
		        User32.INSTANCE.GetWindowText(hwnd, buffer, MAX_TITLE_LENGTH);
		        if (!Native.toString(buffer).contains("Slides"+slideNum)) {
		        	currentWindow = Native.toString(buffer);
		        	writer.write(currentWindow + "\n");
		        	writer.write(Calendar.HOUR + ":" + Calendar.MINUTE+ ":" + Calendar.SECOND + "\n");
		        	Sound.playSound("18.wav", 1000, time, true);
		        	time++;
		        	Thread.sleep(1000);
		        }
	        }
        } catch(FileNotFoundException fnfe) {
        	fnfe.printStackTrace();
        	System.out.println("Lol can't find your meme file");
        }
    }
    
    /**
     * @param slideNumber Slide to test for
     */
    public static void run(int slideNumber) {
    	try {
			main(new String[]{Integer.toString(slideNumber)});
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
 }

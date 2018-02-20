package com.brisk.usualTwo.resources;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public static void playSound(String url, int duration, int time, boolean loop) {
		String directory = System.getProperty("user.dir");
		try {
			
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\com\\brisk\\usualTwo\\resources\\" + url).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        if (loop) { 
	        	@SuppressWarnings("unused")
	        	int k = Clip.LOOP_CONTINUOUSLY; 
	        }
	        clip.start();
	        Thread.sleep(duration);
	        if (!loop)
	        	clip.close();
	        System.out.println("Successfully played file for duration of " + duration + " for the " + time + ((time == 1) ? "st" : (time == 2) ? "nd" : (time == 3)? "rd" : "th") + " time");
	    } catch(Exception ex) {
	        System.out.println("Error:");
	        ex.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		while (true) {
			playSound("18.wav", 1000, 10, true);
		}
	}
	
}
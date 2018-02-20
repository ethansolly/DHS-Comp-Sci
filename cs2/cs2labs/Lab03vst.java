// Lab03vst.java
// Student starting version of the Lab03 assignment.
// Resave this program as Lab03v80 for the 80 point version.
// Resave this program as Lab03v100 for the 100 point version.


package com.ethansolly.cs2labs;

import java.util.Scanner;

public class Lab03vst
{
	public static void main(String[] args)
	{	
		//Obtains number of milliseconds
		Scanner scanner = new Scanner(System.in);
		System.out.println("Lab03, 100 Point Version\n");
		System.out.println("Enter the amount of milliseconds: ");
		long input = Long.parseLong(scanner.next());
	    scanner.close();
	    
	    //Determines number of hours, minutes, seconds, and remaining milliseconds
	    long hours = 	 	  input / (60 * 60 * 1000);
	    long minutes = 		 (input % (60 * 60 * 1000)) / 60000;
	    long seconds = 		((input % (60 * 60 * 1000)) % 60000) / 1000;
	    long msR = 			((input % (60 * 60 * 1000)) % 60000) % 1000; //milliseconds remaining
	    
	    //Outputs previous
	    System.out.println();
	    System.out.println("Starting milli-seconds:\t" + input);
	    System.out.println("Hours:                 \t" + hours);
	    System.out.println("Minutes:               \t" + minutes);
	    System.out.println("Seconds:               \t" + seconds);
	    System.out.println("Milliseconds:          \t" + msR);
	}
}


package com.ethansolly.cs2labs;

// TextLab04st.java
// The Student Records File Program
// This is the student, starting version of the TextLab04 assignment.


import java.io.*;
import java.text.DecimalFormat;


public class TextLab04st
{
	public static void main (String args[]) throws IOException
	{
		System.out.println("\nTextLab04 -- The Student Records File Program\n");
		DecimalFormat df = new DecimalFormat("00.000");
		File f1 = new File("students.dat");
		File f2 = new File("passing.dat");
		File f3 = new File("honors.dat");
		f2.delete();
		f3.delete();
		
		f2.createNewFile();
		f3.createNewFile();
		
		if (f1.exists())
		{
			FileReader inFile = new FileReader(f1);
			BufferedReader inStream = new BufferedReader(inFile);
		    FileWriter passingWriter = new FileWriter(f2);
		    FileWriter honorWriter = new FileWriter(f3);
			String s;
		    String currentName = "";
		    double currentAge = 0;
		    double currentGPA = 0;
		    int count = 0;
		    double meanAge = 0;
		    double meanGPA = 0;
		    int entry = 0;
		    while ((s=inStream.readLine())!=null) {
		    	count++;
		    	entry = count % 3;
		    	switch(entry) {
		    	case 1 :
		    		currentName = s;
		    		System.out.print("Name:\t");
		    		break;
		    	case 2 :
		    		currentAge = Double.parseDouble(s);
		    		System.out.print("Age:\t");
		    		meanAge += currentAge;
		    		break;
		    	case 0 :
		    		currentGPA = Double.parseDouble(s);
		    		if (currentGPA >= 2.0) {
		    			passingWriter.write(currentName + "\r\n");
		    			passingWriter.write(Double.toString(currentAge) + "\r\n");
		    			passingWriter.write(Double.toString(currentGPA) + "\r\n");
		    		}
		    		if (currentGPA >= 3.75) {
		    			honorWriter.write(currentName + "\r\n");
		    			honorWriter.write(Double.toString(currentAge) + "\r\n");
		    			honorWriter.write(Double.toString(currentGPA) + "\r\n");
		    		}
		    		System.out.print("GPA:\t");
		    		meanGPA += currentGPA;
		    		break;
		    	}
		    	System.out.println(s);
		    	if (entry == 0)
		    		System.out.println();
		    }
		    count /= 3;
		    meanAge /= count;
		    meanGPA /= count;
		    System.out.println("Total students records:\t" + df.format(count));
		    System.out.println("Average student age:\t" + df.format(meanAge));
		    System.out.println("Average student GPA:\t" + df.format(meanGPA));
		    inStream.close();
		    passingWriter.close();
		    honorWriter.close();
		}
		else
		{
			System.out.println(f1.getName() + " does not exist");
		}
		System.out.println();
	}
}

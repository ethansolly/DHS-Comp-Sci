package com.ethansolly.cancer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Obo {
	public static void main(String[] args) {
		try {
			FileWriter fw = new FileWriter(new File("fish.rle"));
			Scanner sc = new Scanner(System.in);
			int width = 1000;
			int height = 1000;
			String rule = "B3/S23H";
//			System.out.print("Enter the width of the soup>>\t");
//			int width = sc.nextInt();
//			System.out.print("Enter the height of the soup>>\t");
//			int height = sc.nextInt();
			fw.write("#CXRLE Pos=0,0 Gen=0" 
					+ "\r\n"
					+ "x = " + width 
					+ ", y = " + height 
					+ ", rule = " + rule 
					+ "\r\n");
			for (int a = 0; a < height; a++) {
				for (int b = 0; b < width; b++) {
					boolean isPixel = ((int)(Math.random()*2) == 1);
					if (isPixel)
						fw.write("o");
					else
						fw.write("b");
				}
				fw.write((a == height - 1)?"" : "$");
			}
			fw.write("!");
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

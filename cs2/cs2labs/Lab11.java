package com.ethansolly.cs2labs;

import java.util.*;

/**
 * @author Lutalica
 *
 */
public class Lab11 {
	
	private static String incorrect = "Incorrect; please re-enter";
	private static boolean passCorrect = false;
	private static boolean ageCorrect = false;
	private static boolean gpaCorrect = false;
	private static boolean genderCorrect = false;
	private static int attempts = 0;
	/**
	 * @param args Needed for main method
	 */
	public static void main (String...args) {
		Scanner sc = new Scanner(System.in);
		String pass = "";
		while (!passCorrect) {
			System.out.print("Enter password ==>>\t");
			pass = sc.nextLine();
			passCorrect = pass.equals("SPOCK");
			if (!passCorrect) {
				attempts++;
				if (attempts >= 4) {
					System.out.println(	"\nExcessive password entries\n"
									+ 	"Program aborted\n");
					System.exit(0);
				}
				else
					System.out.println(incorrect);
			}
		}
		int age = -999;
		while (!ageCorrect) {
			System.out.print("Enter age ==>>\t");
			age = sc.nextInt();
			ageCorrect = (age >= 0 && age <= 125);
			if (!ageCorrect)
				System.out.println(incorrect);
		}
		double gpa = -999.0;
		while (!gpaCorrect) {
			System.out.print("Enter GPA ==>>\t");
			gpa = sc.nextDouble();
			gpaCorrect = (gpa >= 0.0 && gpa <= 4.0);
			if (!gpaCorrect)
				System.out.println(incorrect);
		}
		char gender = 'Q';
		while (!genderCorrect) {
			System.out.print("Enter gender ==>>\t");
			gender = sc.next().charAt(0);
			genderCorrect = (gender=='M' || gender=='F');
			if (!genderCorrect)
				System.out.println(incorrect);
		}
		System.out.println();
		System.out.println("Age:\t" 	+ age);
		System.out.println("GPA:\t" 	+ gpa);
		System.out.println("Gender:\t" 	+ gender);
		sc.close();
	}
}

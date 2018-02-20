package com.ethansolly.cs2labs;

// MathLab0404st.java
// The "Sieve of Eratosthenes" Program
// This is the student, starting version of the MathLab04 assignment.

import java.text.DecimalFormat;
import java.util.Scanner;



public class MathLab04st
{
	public static void main(String args[]) 
	{
		// This main method needs additions for the 100 point version.
		Scanner input = new Scanner(System.in); 
		System.out.println("\nMathLab04\nEnter the primes upper bound ===>>\t");
		final int MAX = input.nextInt();
		boolean primes[];
		primes = new boolean[MAX];
		computePrimes(primes);
		displayPrimes(primes);
		input.close();
	}

	public static void computePrimes(boolean primes[])
	{
		System.out.println("\nCOMPUTING PRIME NUMBERS");
		//STEP 01
		for (int i = 1; i < primes.length; i++) {
			primes[i] = true;
			System.out.print(i + " ");
		}
		
		//System.out.println("\nNON-PRIME NUMBERS");
		
		//STEP 02
		for (int i = 1; i < primes.length; i++) {
			for (int j = 2; j <= primes.length; j++) {
				if (i % j == 0 && i > j) {
					primes[i] = false;
					//System.out.print(i + " ");
				}
			}
		}
				  
	}

	public static void displayPrimes(boolean primes[])
	{
		System.out.println("\n\nPRIMES BETWEEN 1 AND "+ primes.length);
		System.out.println();
		DecimalFormat format = new DecimalFormat("0000");
		for (int i = 2; i < primes.length; i++) {
			if (primes[i] == true) {
				System.out.print(format.format(i) + " ");
			}
		}
	}
	
}




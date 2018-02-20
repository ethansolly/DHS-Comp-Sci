package com.ethansolly.cs2labs;

// TextLab06st.java
// The Recursive Methods Program
// This is the student, starting version of the TextLab06 assignment.
// Students need to write the implementations of the
// <countUp>, <countDown>, <sum>, <fact>, <pow>, <fibo>, and <gcf> methods.

	      
public class TextLab06st
{

	public static void main(String args[])
	{
		System.out.println("TextLab06 - The Recursive Methods Program");
		skip2();
		System.out.println("Counting from 1 up to 10");
		Test.countUp(1,10);
		skip3();
		System.out.println("Counting from 10 down to 1");
		Test.countDown(1,10);
		skip3();
		System.out.println("The sum of all integers 0 to 100 are " + Test.sum(100));
		skip2();	
		System.out.println("The factorial of 8 is " + Test.fact(8));
		skip2();
		System.out.println("The 10th Fibonacci Number is " + Test.fibo(10));
		skip2();
		System.out.println("The GCF of 120 and 108 is " + Test.gcf(120,108));
		skip2();
		System.out.println("2 raised to the 8th power is " + Test.pow(2,8));
		skip2();
	}
	
	public static void skip2()
	{
		System.out.println("\n");
	}
	
	public static void skip3()
	{
		System.out.println("\n\n");
	}	
}


class Test
{
	public static void countUp(int k, int n)
	// displays consecutive integers from k to n
	{
		
	}
   
	public static void countDown(int k, int n)
	// displays consecutive integers backwards from k to n
	{
		for (int i = n; i >= k; i--)
			System.out.print(i + " ");
	}
   
	public static int sum(int n)
	// returns the sum of all integers 1 + 2 + .... + n-1 + n
	{
		if (n == 1) return 1;
		else return sum(n-1) + n;
	}
    
	public static int fact(int n)
	// returns n factorial
	{
		int out = 1;
		for (int i = 1; i <= n; i++)
			out *= i;
		return out;
	}
   
	public static int fibo(int n)
	// returns the nth Fibonacci number
	{
		if (n <= 1) return n;
		else return fibo(n-2) + fibo(n-1);
	}

   
	public static int gcf(int n1, int n2)
	// returns the gcf of n1 and n2
	{
		int out;
		for (out = Math.min(n1, n2); out >= 1; out--)
			if (n1 % out == 0 && n2 % out == 0)
				break;
		return out;
	}		
   
	public static int pow(int n1, int n2)
	// returns n1 raised to the n2 power
	{
		if (n2 == 0) return n2;
		if (n2 == 1) return n1;
		else return pow(n1, n2-1) * n1;
	}
   
}    


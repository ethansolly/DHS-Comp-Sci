package com.ethansolly.cs2labs;

// MathLab03st.java
// The Rational Class Program II
// This is the student, starting version of the MathLab03 assignment.
// There are 5 return methods in the Rational class that have temporary return statements
// which allow the program to compile.  Students will need to change these statements.

import javax.swing.JOptionPane;

public class MathLab03st 
{
	public static void main (String args[])
	{   
		String strNum1 = JOptionPane.showInputDialog("Enter Numerator 1"); 
		String strDen1 = JOptionPane.showInputDialog("Enter Denominator 1");
		String strNum2 = JOptionPane.showInputDialog("Enter Numerator 2"); 
		String strDen2 = JOptionPane.showInputDialog("Enter Denominator 2");
		
		int num1 = Integer.parseInt(strNum1);
		int den1 = Integer.parseInt(strDen1);
		int num2 = Integer.parseInt(strNum2);
		int den2 = Integer.parseInt(strDen2);

		Rational2 r1 = new Rational2(num1,den1);
		Rational2 r2 = new Rational2(num2,den2);
		Rational2 r3 = new Rational2();
		
		r3.multiply(r1,r2);
		String mul = r1.getOriginal() + " * " + r2.getOriginal() + "  =  " + r3.getRational();
		r3.divide(r1,r2);
		String div = r1.getOriginal() + " / " + r2.getOriginal() + "  =  " + r3.getRational();		
		r3.add(r1,r2);
		String add = r1.getOriginal() + " + " + r2.getOriginal() + "  =  " + r3.getRational();
		r3.subtract(r1,r2);
		String sub = r1.getOriginal() + " - " + r2.getOriginal() + "  =  " + r3.getRational();
		
		String output = mul + "\n" + div + "\n" + add + "\n" + sub;
		
		JOptionPane.showMessageDialog(null,output);
          
		System.exit(0);
	}
}

		

class Rational2 {
	
	private int firstNum;	// entered numerator
	private int firstDen;	// entered denominator
	private int num;		// reduced numerator
	private int den;		// reduced denominator
	
	public Rational2()
	{
		firstNum = num = 1;
		firstDen = den = 1;
	}

	public Rational2(int n, int d)
	{
		firstNum = num = n;
		firstDen = den = d;
		reduce();
	}
	
	private int getGCF(int n1,int n2)
	{
		int rem = 0;
		int gcf = 0;
		do
		{
			rem = n1 % n2;
			if (rem == 0)
				gcf = n2;
			else
			{
				n1 = n2;
				n2 = rem;
			}
		}
		while (rem != 0);
		return gcf;
	}

	private void reduce() {
		int gcf = getGCF(num, den);
		num = num/gcf;
		den = den/gcf;
	}
	
	public float getDecimal() {
		return (float)(num)/(float)(den);
	}
 
	public String getRational() {
		return Integer.toString(num) + ((den!=1)? ("/" + Integer.toString(den)) : "");
	}
	
	public String getOriginal() {
		return Integer.toString(firstNum) + "/" + Integer.toString(firstDen);
	}
	
	public int getNum()
	{
		return num;
	}

	public int getDen()
	{
		return den;
	}
	
	public void multiply(Rational2 r1, Rational2 r2)
	{
		num = r1.num * r2.num;
		den = r1.den * r2.den;
		reduce();
	}

	public void divide(Rational2 r1, Rational2 r2)
	{
		num = r1.num * r2.den;
		den = r1.den * r2.num;
		reduce();
	}
	
	public void add(Rational2 r1, Rational2 r2)
	{
		int num1 = r1.num;
		int den1 = r1.den;
		int num2 = r2.num;
		int den2 = r2.den;
		
		int lcm = getLCM(den1, den2);
		num = (num1*den2) + (num2*den1);
		den = lcm;
	}
	
	public void subtract(Rational2 r1, Rational2 r2)
	{
		int num1 = r1.num;
		int den1 = r1.den;
		int num2 = r2.num;
		int den2 = r2.den;
		
		int lcm = getLCM(den1, den2);
		num = (num1*den2) - (num2*den1);
		den = lcm;
	}
	
	private int getLCM(int n1, int n2) {
		int gcf = getGCF(n1, n2);
		return (n1*n2)/gcf;
	}
}
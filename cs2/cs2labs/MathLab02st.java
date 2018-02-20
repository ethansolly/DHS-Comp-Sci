package com.ethansolly.cs2labs;

// MathLab02st.java
// The Rational Class Program I
// This is the student, starting version of the MathLab02 assignment.

import javax.swing.JOptionPane;

public class MathLab02st
{
	public static void main (String args[])
	{  
		while(true) {
			String strNbr1 = JOptionPane.showInputDialog("Enter Numerator "); 
			String strNbr2 = JOptionPane.showInputDialog("Enter Denominator ");
	
			int num = Integer.parseInt(strNbr1);
			int den = Integer.parseInt(strNbr2);
	
			Rational r = new Rational(num,den);
			JOptionPane.showMessageDialog(null,r.getOriginal() + " equals " + r.getDecimal() + " and reduces to " + r.getRational());
		}
	}
}

class Rational
{
	private int firstNum;
	private int firstDen;
	private int num;
	private int den;
	
//	Rational
	public Rational(int parNum, int parDen) {
		firstNum = num = parNum;
		firstDen = den = parDen;
		reduce();
	}

	
//	getNum
	public int getNum() {
		return num;
	}
	
//	getDen
	public int getDen() {
		return den;
	}

//	getDecimal
	public float getDecimal() {
		return (float)(num)/(float)(den);
	}

//	getRational 
	public String getRational() {
		return num + ((den!=1)? ("/" + Integer.toString(den)) : "");
	}
	
//	getOriginal
	public String getOriginal() {
		return Integer.toString(firstNum) + "/" + Integer.toString(firstDen);
	}
	
//	reduce
	private void reduce() {
		int gcf = getGCF(num, den);
		num = num/gcf;
		den = den/gcf;
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
}
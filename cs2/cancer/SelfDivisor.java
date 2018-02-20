package com.ethansolly.cancer;

public class SelfDivisor {

	public static boolean isSelfDivisor(int number) {
		boolean out = true;
		char[] digitChars = Integer.toString(number).toCharArray();
		int digit;
		for (int i = 0; i < digitChars.length; i++) {
			digit = Integer.parseInt("" + digitChars[i]);
			if ((digit == 0) || !(number % digit == 0))
				out = false;
		}
		return out;
	}
	
	public static int[] firstNumSelfDivisors(int start, int num) {
		int[] out = new int[num];
		int got = 0;
		int test = start;
		boolean foundAll = false;
		while (!foundAll) {
			if (isSelfDivisor(test))
				out[got++] = test++;
			if (got == num)
				foundAll = true;
		}
		return out;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			System.out.println(firstNumSelfDivisors(10, 3)[i]);
		}
	}

}

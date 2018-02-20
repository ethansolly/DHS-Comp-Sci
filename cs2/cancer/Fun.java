package com.ethansolly.cancer;

public class Fun {
	public static boolean g() {
		return Math.random() > 0.5;
	}
	
	public static void main(String[] args) {
		int wrongs = 0;
		int corrects = 0;
		while (true) {	
			boolean a = !g();
			if (a != !g()) {
				wrongs++;
			} else {
				corrects++;
			}
			System.out.println(
					"Wrongs:\t\t"	+wrongs+
					"\nCorrects:\t"	+corrects);
		}
	}
	
}

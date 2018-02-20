package labs.Lab23a;// Lab23ast.java
// This is the starting version of the "Perfect Number" program. 


import java.util.*;


public class Lab23ast
{
	public static void main (String args[]) 
	{   
		System.out.println("Lab 23 80 Points Version");
        System.out.println();

        System.out.print("Enter an integer in [2..10000] range ===>>  ");

        int in = new Scanner(System.in).nextInt();
        System.out.println();

        for (int h = 1; h <= in; h++) {
            if (h != 1) {
                int sum = 1;
                String out = "1";
                for (int i = 2; i <= h / 2; i++) {
                    if (h % i == 0) {
                        out += " + " + i;
                        sum += i;
                    }
                }

                out += " = " + sum;

                if (sum == h) {
                    System.out.println(out);
                    System.out.println(h + " is a perfect number");
                    System.out.println();
                }
            }
        }
	}
       
}

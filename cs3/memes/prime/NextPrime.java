package memes.prime;

import java.util.Scanner;

public class NextPrime {
    public static void main(String...args) {
        Scanner sc = new Scanner(System.in);
        int p = 2;
        int i = 1;
        while(true) {
            if (i % 4 == 0) {
                System.out.println(p);
                //sc.nextLine();
                while (!isPrime(++p)) ;
            }
            i++;
        }
    }

    public static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= Math.sqrt(num); i += 2)
            if (num % i == 0) return false;
        return true;
    }

}

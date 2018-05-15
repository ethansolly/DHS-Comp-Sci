package memes.USAMO.Year2018;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Problem3 {

    public static void main(String...args) {

        /*
        try {
            File f = new File("cs3/memes/USAMO/Year2018/sets");
            FileWriter fw = new FileWriter(f);
            for (int i = 1; i < 10000; i++) {
                int m = totientFunct(i);
                if (i % m == 0) {
                    List list = getCoprimeList(i);
                    fw.write(i + " : " + list.toString());
                    fw.write("\n");
                }
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        //8192 is a valid n such that m divides n
        int n = 256;
        List<Integer> list = getCoprimeList(n);
        int m = list.size();
        System.out.println("m : " + m + "\nn : " + n);
        for (int k = 0; k < 10; k++) {
            BigInteger sum = BigInteger.ZERO;
            for (int a : list) {
                sum = sum.add(new BigInteger("" + a).pow(k));
            }
            BigInteger mod = sum.mod(new BigInteger("" + m));
            if (mod.equals(BigInteger.ZERO))
                System.out.println("k = " + k + " :: " + sum.toString() + " is divisible by " + m);
            else {
                System.out.println("k = " + k + " :: " + sum.toString() + " is NOT divisible by " + m + "...mod = " + mod.toString());
            }


        }


    }

    public static boolean areCoprime(int a, int b) {
        return gcd(a, b) == 1;
    }

    public static int gcd(int a, int b) {
        return (b==0) ? a : gcd(b, a%b);
    }

    /** Returns the set of all positive integers less than n that are coprime with n.
     *
     */
    public static List<Integer> getCoprimeList(int n) {

        List<Integer> ret = new ArrayList<>();

        ret.add(1);

        for (int i = 2; i <= n / 2; i++) {
            if (areCoprime(i, n)) {
                ret.add(i);
            }
        }

        if (n > 2)
            for (int i = ret.size() - 1; i >= 0; i--)
                ret.add(n - ret.get(i));

        return ret;
    }

    public static Set<Integer> getPrimeFactors(int n) {

        Set<Integer> ret = new TreeSet<>();

        for (int i = 2; i <= Math.sqrt(n); i++) {
            while ( n % i == 0) {
                ret.add(i);
                n /= i;
            }
        }

        if (n >= 2)
            ret.add(n);

        return ret;
    }

    public static int totientFunct(int n) {

        double prod = (double)n;

        for (int factor : getPrimeFactors(n)) {
            prod *= (1.0 - 1.0/((double)factor));
        }

        return (int)prod;

    }

}
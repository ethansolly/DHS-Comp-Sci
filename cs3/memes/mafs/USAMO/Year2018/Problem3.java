package memes.mafs.USAMO.Year2018;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Problem3 {

    public static void main(String...args) {

        /** Generate the numbers n such that:
         *  Given a prime factor p of n, the prime factors of p-1 are also prime factors of n.
         */


        try {
            int n = 1;
            int MAX = 10000;
            FileWriter fw = new FileWriter(new File("cs3/memes/mafs/USAMO/Year2018/coolnumbers"));
            while (n <= MAX) {
                Set<Integer> factors = getPrimeFactors(n);
                boolean isCoolNumber = true;
                for (int f : factors) {
                    //Get factors of f-1
                    Set<Integer> subfactors = getPrimeFactors(f-1);

                    //Check if n contains those factors
                    if (!factors.containsAll(subfactors)) {
                        isCoolNumber = false;
                        break;
                    }

                }
                if (isCoolNumber)
                    fw.write(n + "\n");
                n++;
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
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
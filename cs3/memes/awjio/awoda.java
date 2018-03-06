package memes.awjio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class awoda {

    private static int[][] matr = new int[][] {
            { 7,  9,  5,  6},
            {10, 11, 15, 12},
            {13, 15, 12, 21},
            {17, 38, 65, 96},
            {16, 12, 14, 15}
    };

    public static void main(String...args) {
        try {
            Scanner sc = new Scanner(new File("cs3/memes/awjio/awo.dat"));
            int rowTot = sc.nextInt();
            int colTot = 4;
            int c = sc.nextInt();
            int r = sc.nextInt();

            boolean first = true;
            int maxSum = 0;

            for(int i = 0; i <= rowTot-r; i++) {
                for (int j = 0; j <= colTot-c; j++) {

                    int sum = 0;

                    for(int I = i; I < i+r; I++) {
                        for (int J = j; J < j+c; J++) {
                            sum += matr[I][J];
                        }
                    }

                    if (first || (sum > maxSum)) {
                        first = false;
                        maxSum = sum;
                    }
                }
            }

            System.out.println(maxSum);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

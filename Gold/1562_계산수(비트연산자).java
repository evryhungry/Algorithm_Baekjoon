import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1562
public class Main {
    static int MOD = 1000000000;
    static private int n ;
    static private long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        dp = new long[n+1][11][1<<10];

        for (int i = 1 ; i < 10 ; i++){
            dp[1][i][1<<i] = 1;
        }

        /**
         *  i : i자리 숫자
         *  j 끝나는 숫자 , k 마킹된 숫자
         ex) 	j=9, 10 0000 0000

         k =1, 10 0000 0001
         ...
         k = 9, 10 0000 1001
         ...
         k = 1023, 11 1111 1111

         i : 2, j : 3, k : 00 0001 1100 : 28
         */

        for(int i = 2 ; i < n+1 ; i++){
            for (int j = 0 ; j < 10 ; j++) {
                for (int k = 0; k < 1024; k++) {
                    int bit = k | (1 << j);
                    if (j == 0) {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j + 1][k]) % MOD;
                    } else if (j == 9) {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j - 1][k]) % MOD;
                    } else {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j + 1][k] + dp[i - 1][j - 1][k]) % MOD;
                    }
                }
            }
        }

        long sum = 0;
        for (int i = 0 ; i < 10 ; i++){
            sum = (sum + dp[n][i][1023]) % MOD;
        }

        System.out.println(sum);
    }
}
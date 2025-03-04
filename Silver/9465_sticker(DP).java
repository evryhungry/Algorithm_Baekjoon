import java.io.*;
import java.util.*;

// https://www.acmicpc.net/submit/9465
public class Main {
    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            int[][] stickers = new int[2][n+1];
            int[][] dp = new int[2][n+1];
            for (int j = 0; j < 2; j++){
                String[] temp = br.readLine().split(" ");
                for (int k = 1; k <= n; k++){
                    stickers[j][k] = Integer.parseInt(temp[k - 1]);
                }
            }

            dp[0][1] = stickers[0][1];
            dp[1][1] = stickers[1][1];

            for(int j = 2 ; j <= n; j++){
                dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + stickers[0][j];
                dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + stickers[1][j];
            }

            bw.write(Math.max(dp[0][n], dp[1][n]) + "\n");
        }

        bw.flush();
        bw.close();
    }
}
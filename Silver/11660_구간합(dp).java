import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11660
public class Main {
    static int n, m;
    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n+1][n+1];
        dp = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = Integer.parseInt(s[j-1]);
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j];
            }
        }


        for (int i = 0 ; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int sum = dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1];

            bw.write(sum + "\n");
        }
        bw.flush();
        br.close();
    }
}

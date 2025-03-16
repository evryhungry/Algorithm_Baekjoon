import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/17070
public class Main {
    static int n;
    static int[][] map;
    static int[][] dp ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1, 0);
        System.out.println(dp[n-1][n-1]);
    }

    // r == 0 : 가로, 1: 대각선, 2: 세로
    static void dfs(int i, int j, int r) {
        if (i >= n || j >= n || r > 2 || map[i][j] == 1) return;

        if (r == 0){
            dfs(i, j+1, r);
            dfs(i+1, j+1, r+1);
        } else if (r == 1){
            if (map[i-1][j] == 1 || map[i][j-1] == 1) return;
            dfs(i, j+1, r-1);
            dfs(i+1, j+1, r);
            dfs(i+1, j, r+1);
        } else {
            dfs(i+1, j+1, r-1);
            dfs(i+1, j, r);
        }
        dp[i][j]++;
    }
}

import java.io.*;
import java.util.StringTokenizer;

// https://www.acmicpc.net/submit/17404

/**
 * 1번째 틀린 이유 : or (int c = 0; c < 3; c++) dp[1][c] = Integer.MAX_VALUE; -> 오버플로우
 *
 * DP 문제
 * 초기화 : dp[1] = { color, INF, INF }
 * INF 하는 이유 -> 다른 경로에 대한 원천 차단.
 *
 * 2번째 틀린 이유 : 경로 차단을 안함. fuxx
 *
 */
public class Main{

    static int N;
    static int[][] cost;


    private static int dp(){
        int ans = Integer.MAX_VALUE;

        for (int start = 0; start < 3; start++) {
            int[][] dp = new int[N + 1][3];

            for (int c = 0; c < 3; c++) dp[1][c] = 1000000000;
            dp[1][start] = cost[1][start];

            for (int i = 2; i <= N; i++) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
            }

            for (int last = 0; last < 3; last++) {
                if (last == start) continue;
                ans = Math.min(ans, dp[N][last]);
            }
        }

        return ans ;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        cost = new int[N + 1][3];
        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(String.valueOf(dp()));
        bw.flush();
        bw.close();
    }
}
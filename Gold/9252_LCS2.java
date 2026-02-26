import java.io.*;

/**
 * https://www.acmicpc.net/problem/9252
 *
 * DP 를 이용한 동일 순열 찾기.
 * 같은 단어가 없으면 넘어가기, 있으면 해당 위치에 이전 순열의 길이값 + 1;
 */
public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String a = br.readLine();
        String b = br.readLine();
        int a_len = a.length();
        int b_len = b.length();


        int[][] dp = new int[a_len + 1][b_len + 1];
        for (int i = 1; i <= a_len; i++) {
            char ca = a.charAt(i - 1);
            for (int j = 1; j <= b_len; j++) {
                char cb = b.charAt(j - 1);
                if (ca == cb) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int len = dp[a_len][b_len];
        StringBuilder sb = new StringBuilder();
        sb.append(len).append("\n");

        if (len == 0) {
            bw.write(sb.toString());
            bw.flush();
            return;
        }

        StringBuilder lcs = new StringBuilder();
        while (a_len > 0 && b_len > 0) {
            if (a.charAt(a_len - 1) == b.charAt(b_len - 1)) {
                lcs.append(a.charAt(a_len - 1));
                a_len--;
                b_len--;
            } else {
                if (dp[a_len - 1][b_len] >= dp[a_len][b_len - 1]) a_len--;
                else b_len--;
            }
        }

        sb.append(lcs.reverse());
        bw.write(sb.toString());
        bw.flush();
    }
}
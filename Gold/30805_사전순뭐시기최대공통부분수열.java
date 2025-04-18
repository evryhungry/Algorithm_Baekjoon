import java.io.*;
import java.util.*;

public class Main {
    static int[] A, B;
    static int N, M;
    static String[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) A[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        B = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) B[i] = Integer.parseInt(st.nextToken());

        dp = new String[N + 1][M + 1];

        for (int i = 0; i <= N; i++)
            Arrays.fill(dp[i], "");

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + A[i] + " ";
                } else {
                    dp[i][j] = maxString(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        String[] result = dp[N][M].trim().split(" ");
        if (result.length == 1 && result[0].isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(result.length);
            for (String s : result) {
                System.out.print(s + " ");
            }
        }
    }

    static String maxString(String a, String b) {
        String[] aSplit = a.trim().split(" ");
        String[] bSplit = b.trim().split(" ");
        int len = Math.min(aSplit.length, bSplit.length);

        for (int i = 0; i < len; i++) {
            int numA = Integer.parseInt(aSplit[i]);
            int numB = Integer.parseInt(bSplit[i]);
            if (numA != numB) {
                return numA > numB ? a : b;
            }
        }
        return aSplit.length >= bSplit.length ? a : b;
    }
}
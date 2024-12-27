import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, answer;
    static int[][] matA;
    static int[][] matB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matA = new int[n][m];
        matB = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                matA[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                matB[i][j] = str.charAt(j) - '0';
            }
        }

        answer = solve();
        System.out.println(answer);
    }

    static int solve() {
        if (n < 3 || m < 3) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (matA[i][j] != matB[i][j]) return -1;
                }
            }
            return 0;
        }

        int rate = 0;
        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= m - 3; j++) {
                if (matA[i][j] != matB[i][j]) {
                    flip3x3(i, j);
                    rate++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matA[i][j] != matB[i][j]) return -1;
            }
        }

        return rate;
    }

    static void flip3x3(int x, int y) {
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                matA[i][j] = 1 - matA[i][j];
            }
        }
    }
}

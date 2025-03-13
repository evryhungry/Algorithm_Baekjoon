import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1987
public class Main {
    static int n, m, max = 0;
    static char[][] map;
    static boolean[] visited = new boolean[26];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        dfs(0, 0, 1);

        System.out.println(max);
    }

    public static void dfs(int i, int j, int count) {
        max = Math.max(max, count);
        visited[map[i][j] - 'A'] = true;

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if (!visited[map[nx][ny] - 'A']) {
                    dfs(nx, ny, count + 1);
                }
            }
        }

        visited[map[i][j] - 'A'] = false;
    }
}

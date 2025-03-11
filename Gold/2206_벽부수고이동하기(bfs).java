import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2206
public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if (n == 1 && m == 1) {
            System.out.println("1");
            return;
        }

        map = new int[n+1][m+1];
        visited = new boolean[n+1][m+1][2];
        for (int i = 1; i < n+1; i++) {
            String line = br.readLine();
            for (int j = 1; j < m+1; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }

        int cnt = bfs(1, 1);
        System.out.println(cnt);
    }

    public static int bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        visited[i][j][0] = true;
        q.add(new int[]{i, j, 0, 1});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0], curY = cur[1];
            int wallBreaking = cur[2];
            int dist = cur[3];

            if (curX == n && curY == m) {
                return dist;
            }

            for (int k = 0; k < 4; k++) {
                int nx = curX + dx[k];
                int ny = curY + dy[k];

                if(nx <= 0 || nx > n || ny <= 0 || ny > m) continue;

                if (map[nx][ny] == 0 && !visited[nx][ny][wallBreaking]) {
                    visited[nx][ny][wallBreaking] = true;
                    q.add(new int[]{nx, ny, wallBreaking, dist + 1});
                }

                if (map[nx][ny] == 1 && wallBreaking == 0 && !visited[nx][ny][1]) {
                    visited[nx][ny][1] = true;
                    q.add(new int[]{nx, ny, 1, dist + 1});
                }
            }
        }
        return -1;
    }
}

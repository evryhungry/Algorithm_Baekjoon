import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2638
class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n, m, days = 0;
    static int cheeseCnt;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Point> cheese;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        cheese = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    cheeseCnt++;
                    cheese.add(new Point(i, j));
                }
            }
        }

        while (cheeseCnt > 0) {
            visited = new boolean[n][m];
            bfs(0, 0);
            matching();
            days++;
        }

        System.out.println(days);
    }

    static void matching(){
        for (int i = cheese.size() - 1; i >= 0; i--) {
            Point p = cheese.get(i);
            int count = 0;

            for (int j = 0 ; j < 4 ; j ++){
                int bx = p.x + dx[j];
                int by = p.y + dy[j];

                if (bx < 0 || by < 0 || bx >= n || by >= m) continue;
                if (map[bx][by] == 2) count++; // 면을 맞닿아 있는 공기의 수
            }

            if (count >= 2){
                map[p.x][p.y] = 0; // 치즈 공간 초기화
                cheese.remove(i);
                cheeseCnt--;
            }
        }
    }

    static void bfs(int i, int j) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];

                if (nx >= n || ny >= m || nx < 0 || ny < 0) continue;
                if (map[nx][ny] == 1 || visited[nx][ny]) continue;

                map[nx][ny] = 2;
                visited[nx][ny] = true;
                q.add(new Point(nx, ny));
            }
        }
    }
}
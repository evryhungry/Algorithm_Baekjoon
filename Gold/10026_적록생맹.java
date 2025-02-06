import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/10026
public class Main {
    static int N, cnt;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] colors;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        colors = new char[N][N];
        visited = new boolean[N][N];


        for (int i = 0; i < N; i++) {
            colors[i] = br.readLine().toCharArray();
        }

        StringBuilder sb = new StringBuilder();
        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    cnt++;
                    bfs(i, j);
                }
            }
        }

        sb.append(cnt + " ");
        cnt = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    cnt++;
                    bfs(i, j);
                }
            }
        }
        sb.append(cnt + " ");
        bw.write(sb.toString().trim());
        bw.flush();
    }

    static void bfs(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        visited[y][x] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int nowY = cur[0];
            int nowX = cur[1];

            for (int i = 0; i < 4; i++) {
                int newY = nowY + dx[i];
                int newX = nowX + dy[i];

                if(newX < 0 || newX >= N || newY < 0 || newY >= N) continue;
                if(visited[newY][newX] || colors[nowY][nowX] != colors[newY][newX]) continue;

                visited[newY][newX] = true;
                q.add(new int[]{newY, newX});
            }
            if(colors[nowY][nowX] == 'R')
                colors[nowY][nowX] = 'G';
        }
    }
}

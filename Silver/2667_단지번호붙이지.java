import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2667
public class Main {
    static int N ,count;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited = new boolean[25][25];
    static int[][] map = new int[25][25];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) map[i][j] = s.charAt(j) - '0';
        }

        ArrayList<Integer> apart = new ArrayList<>();
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count = 0;
                if (map[i][j] == 1 && visited[i][j] == false) {
                    bfs(i, j);
                    apart.add(count);
                    result++;
                }
            }
        }

        apart.sort(null);
        System.out.println(result);
        for (int i = 0; i < apart.size(); i++) System.out.println(apart.get(i));
    }

    static void bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while(!q.isEmpty()){
            count++;
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] == 1 && visited[nx][ny] == false) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }
}

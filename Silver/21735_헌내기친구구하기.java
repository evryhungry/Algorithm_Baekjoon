import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/21736
public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int cnt = 0, X, Y;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        map = new char[X][Y];
        visited = new boolean[X][Y];
        int startX = -1, startY = -1;

        for (int i = 0; i < X; i++) {
            String s = br.readLine();
            for (int j = 0; j < Y; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'I') {
                    startX = i;
                    startY = j;
                }
            }
        }

        System.out.println(bfs(startX, startY) == 0 ? "TT" : cnt);
    }

    static int bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while(!q.isEmpty()){
            int[] current = q.poll();

            for (int i = 0; i < 4; i++) {
                int newX = current[0] + dx[i];
                int newY = current[1] + dy[i];

                if(newX < 0 || newX >= X || newY < 0 || newY >= Y) continue;
                if(visited[newX][newY] || map[newX][newY] == 'X') continue;

                visited[newX][newY] = true;
                q.offer(new int[]{newX, newY});

                if(map[newX][newY] == 'P') cnt++;
            }
        }

        return cnt;
    }
}

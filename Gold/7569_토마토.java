import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/7569
public class Main {
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};
    static int[][][] map;
    static int m, n, h;
    static Queue<int[]> q = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][n][m]; // h, y, x 순

        for(int i = 0 ; i < h ; i++){
            for(int j = 0 ; j < n ; j++){
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());

                    if (map[i][j][k] == 1) {
                        q.add(new int[]{i, j, k});
                    }
                }
            }
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        while(!q.isEmpty()){
            int[] cur = q.poll();

            for (int i = 0; i < 6; i++){
                int nh = cur[0] + dh[i];
                int ny = cur[1] + dy[i];
                int nx = cur[2] + dx[i];

                if (nh >= 0 && ny >= 0 && nx >= 0 && nx < m && ny < n && nh < h){
                    if (map[nh][ny][nx] == 0){
                        map[nh][ny][nx] = map[cur[0]][cur[1]][cur[2]] + 1;
                        q.add(new int[]{nh, ny, nx});
                    }
                }
            }
        }

        int days = 0;
        for(int i = 0 ; i < h ; i++){
            for(int j = 0 ; j < n ; j++){
                for (int k = 0; k < m; k++) {
                    if (map[i][j][k] == 0) return -1;
                    days = Math.max(days, map[i][j][k]);
                }
            }
        }

        return days - 1;
    }
}

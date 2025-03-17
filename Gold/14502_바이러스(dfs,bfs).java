import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14502
public class Main {
    static int n, m;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int safeLocationCount = Integer.MIN_VALUE;

    static class Virus {
        int x;
        int y;

        public Virus (int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(safeLocationCount);
    }

    // 1. 벽 3개 세우기
    static void dfs(int wallCnt){
        if(wallCnt == 3){
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    dfs(wallCnt + 1);
                    map[i][j] = 0;
                }
            }
        }


    }

    // 2 바이러스 퍼지고
    static void bfs(){
        Queue<Virus> q = new LinkedList<>();
        int[][] tempMap = new int[n][m]; // 이거 안해서 항상 0 나옴.

        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, tempMap[i], 0, m);
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(tempMap[i][j] == 2){
                    q.add(new Virus(i, j));
                }
            }
        }

        while(!q.isEmpty()){
            Virus virus = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = virus.x + dx[i];
                int ny = virus.y + dy[i];

                if(nx >= 0 && nx < n && ny >= 0 && ny < m){
                    if(tempMap[nx][ny] == 0){
                        tempMap[nx][ny] = 2;
                        q.add(new Virus(nx, ny));
                    }
                }
            }
        }

        countZero(tempMap);
    }

    // 3. 안전함 집들 세고.
    static void countZero(int[][] tempMap){
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(tempMap[i][j] == 0){
                    count++;
                }
            }
        }

        safeLocationCount = Math.max(safeLocationCount, count);
    }
}
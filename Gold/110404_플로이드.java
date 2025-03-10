import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11404
public class Main {
    static int INF = 10000007;
    static int n, m ;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        map = new int[n+1][n+1];
        for (int i = 1; i < n+1; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[start][end] = Math.min(weight, map[start][end]);
        }
        br.close();

        Floyd_warshall();

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <n+1 ; i++){
            for (int j = 1; j <n+1 ; j++) {
                if (map[i][j] == INF) {
                    sb.append("0" + " ");
                    continue; // 2번째 틀린 이유 StringBuilder 2번 호출로 중복 호출
                }
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void Floyd_warshall() {
        for(int k = 1; k <n+1 ; k++){
            for(int i = 1; i <n+1 ; i++){
                for(int j = 1; j <n+1 ; j++){
                    if (map[i][k] != INF && map[k][j] != INF) { // 1 번째 틀린 이유 오버플로우
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }
        }
    }
}

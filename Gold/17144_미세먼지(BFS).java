import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/17144
public class Main {
    static int r, c, t;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int airPurifier1;
    static int airPurifier2;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        catchAirPurifier();
        for (int i = 0 ; i < t ; i++) { solve(); }
        count();

        bw.write(cnt + "\n");
        bw.flush();
    }

    public static void catchAirPurifier(){
        for (int i = 0; i < r; i++) {
            if (map[i][0] == -1){
                airPurifier1 = i;
                airPurifier2 = i + 1;
                break;
            }
        }
    }

    public static void solve(){
        map = dustSimulation();
        operateAirPurifier();
    }

    public static int[][] dustSimulation() {
        int[][] temp = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == -1) {
                    temp[i][j] = -1;
                    continue;
                }
                int spreadAmount = map[i][j] / 5;
                int spreadCount = 0;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx >= 0 && ny >= 0 && nx < r && ny < c && map[nx][ny] != -1) {
                        temp[nx][ny] += spreadAmount;
                        spreadCount++;
                    }
                }
                temp[i][j] += map[i][j] - (spreadAmount * spreadCount);
            }
        }

        return temp;
    }

    public static void operateAirPurifier(){
        int top = airPurifier1;
        for (int x = top - 1; x > 0; x--) {
            map[x][0] = map[x - 1][0];
        }
        for (int y = 0; y < c - 1; y++) {
            map[0][y] = map[0][y + 1];
        }
        for (int x = 0; x < top; x++) {
            map[x][c - 1] = map[x + 1][c - 1];
        }
        for (int y = c - 1; y > 1; y--) {
            map[top][y] = map[top][y - 1];
        }
        map[top][1] = 0;

        int bottom = airPurifier2;
        for (int x = bottom + 1; x < r - 1; x++) {
            map[x][0] = map[x + 1][0];
        }
        for (int y = 0; y < c - 1; y++) {
            map[r - 1][y] = map[r - 1][y + 1];
        }
        for (int x = r - 1; x > bottom; x--) {
            map[x][c - 1] = map[x - 1][c - 1];
        }
        for (int y = c - 1; y > 0; y--) {
            map[bottom][y] = map[bottom][y - 1];
        }
        map[bottom][1] = 0;
    }

    public static void count(){
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == -1) { continue; }
                cnt += map[i][j];
            }
        }
    }
}
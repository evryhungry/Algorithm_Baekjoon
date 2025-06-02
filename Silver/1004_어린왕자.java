import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1004
public class Main {
    static int n;
    static int  x1, y1, x2, y2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int count = 0;

            for (int j = 1; j <= m; j++) {
                st = new StringTokenizer(br.readLine());
                int roundX = Integer.parseInt(st.nextToken());
                int roundY = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                int dist1 = getSquaredDistance(x1, y1, roundX, roundY);
                int dist2 = getSquaredDistance(x2, y2, roundX, roundY);
                int rSq = r * r;

                if ((dist1 < rSq) != (dist2 < rSq)) {
                    count++;
                }
            }

            sb.append(count).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static int getSquaredDistance(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return dx * dx + dy * dy;
    }
}
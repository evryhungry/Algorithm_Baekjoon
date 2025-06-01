import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1002
public class Main {
    static int n;
    static double x1, y1, r1, x2, y2, r2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Double.parseDouble(st.nextToken());
            y1 = Double.parseDouble(st.nextToken());
            r1 = Double.parseDouble(st.nextToken());
            x2 = Double.parseDouble(st.nextToken());
            y2 = Double.parseDouble(st.nextToken());
            r2 = Double.parseDouble(st.nextToken());

            if(x1 == x2 && y1 == y2 && r1 == r2) {
                sb.append(-1).append("\n");
                continue;
            }

            double distSq = getSquaredDistance(x1, y1, x2, y2);
            double sumR = r1 + r2;
            double diffR = Math.abs(r1 - r2);

            if (distSq == sumR * sumR || distSq == diffR * diffR) {
                sb.append(1).append("\n");
            } else if (distSq < diffR * diffR || distSq > sumR * sumR) {
                sb.append(0).append("\n");
            } else {
                sb.append(2).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static double getSquaredDistance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return dx * dx + dy * dy;
    }
}
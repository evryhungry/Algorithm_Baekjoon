import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1007
public class Main {
    static int n, m;
    static int x, y;
    static Point[] points;
    static boolean[] selected;
    static double minLength ;

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            m = Integer.parseInt(br.readLine());
            points = new Point[m];
            selected = new boolean[m];
            minLength = Double.MAX_VALUE;

            for (int j = 0; j < m; j++) {
                String line = br.readLine();
                x = Integer.parseInt(line.trim().split(" ")[0]);
                y = Integer.parseInt(line.trim().split(" ")[1]);
                points[j] = new Point(x, y);
            }

            backtrack(0, 0);
            sb.append(String.format("%.8f", minLength)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static void backtrack(int idx, int count) {
        if (count == m / 2) {
            computeVectorSum();
            return;
        }

        for (int i = idx; i < m; i++) {
            if (!selected[i]) {
                selected[i] = true;
                backtrack(i + 1, count + 1);
                selected[i] = false;
            }
        }
    }

    static void computeVectorSum() {
        int sumX = 0;
        int sumY = 0;

        for (int i = 0; i < m; i++) {
            if (selected[i]) {
                sumX += points[i].x;
                sumY += points[i].y;
            } else {
                sumX -= points[i].x;
                sumY -= points[i].y;
            }
        }

        double length = Math.sqrt((double) sumX * sumX + (double) sumY * sumY);
        minLength = Math.min(minLength, length);
    }


}
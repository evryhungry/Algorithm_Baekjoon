import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2096
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] maxMap = new int[3];
        int[] minMap = new int[3];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (i == 0){
                maxMap[0] = a;
                maxMap[1] = b;
                maxMap[2] = c;
                minMap[0] = a;
                minMap[1] = b;
                minMap[2] = c;
            } else {
                int befor_maxMap0 = maxMap[0], befor_maxMap2 = maxMap[2];
                maxMap[0] = Math.max(maxMap[0], maxMap[1]) + a;
                maxMap[2] = Math.max(maxMap[1], maxMap[2]) + c;
                maxMap[1] = Math.max(Math.max(befor_maxMap0, maxMap[1]), befor_maxMap2) + b;

                int befor_minMap0 = minMap[0], befor_minMap2 = minMap[2];
                minMap[0] = Math.min(minMap[1], minMap[0]) + a;
                minMap[2] = Math.min(minMap[1], minMap[2]) + c;
                minMap[1] = Math.min(Math.min(befor_minMap0, minMap[1]), befor_minMap2) + b;
            }
        }

        sb.append(Math.max(Math.max(maxMap[0], maxMap[1]), maxMap[2]))
                .append(' ')
                .append(Math.min(Math.min(minMap[0], minMap[1]), minMap[2]))
                .append('\n');
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

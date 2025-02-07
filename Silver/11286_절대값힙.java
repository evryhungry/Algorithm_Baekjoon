import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11286
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> compare(o1, o2));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(br.readLine());

            if (k == 0) {
                if(pq.isEmpty()) {
                    sb.append("0\n");
                }
                else {
                    sb.append(pq.poll()).append("\n");
                }
            }
            else {
                pq.offer(k);
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static int compare(Integer o1, Integer o2) {
        if(Math.abs(o1) > Math.abs(o2)) {
            return Math.abs(o1) - Math.abs(o2);
        }else if(Math.abs(o1) == Math.abs(o2)) {
            return o1 - o2;
        } else {
            return -1;
        }
    }
}


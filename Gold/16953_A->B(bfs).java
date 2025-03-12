import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/16953
public class Main {
    static long a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Long> q = new LinkedList<Long>();
        q.add(a);
        int count = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            count++;

            for (int i = 0; i < size; i++) {
                long cur = q.poll();
                if (cur > b) continue;
                if (cur == b) { return count++; }

                q.add(cur * 2);
                q.add(cur * 10 + 1);
            }
        }
        return -1;
    }
}

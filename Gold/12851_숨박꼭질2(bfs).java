import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/12851
public class Main {
    static int s, b;
    static int count = 0;
    static int min = Integer.MAX_VALUE;
    static int[] visited = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        if (s == b){
            System.out.println(0);
            System.out.println(1);
            return;
        }

        bfs(s);

        System.out.println(min);
        System.out.println(count);
    }

    public static void bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        visited[n] = 1;
        q.add(n);

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (min < visited[cur]) { return; }

            for (int i = 0; i < 3; i++) {
                int next;

                if (i == 0) next = cur + 1;
                else if (i == 1) next = cur - 1;
                else next = cur * 2;

                if (next < 0 || next > 100000) continue;

                if (next == b) {
                    min = visited[cur];
                    count++;
                }

                if (visited[next] == 0 || visited[next] == visited[cur] + 1) {
                    q.add(next);
                    visited[next] = visited[cur] + 1;
                }
            }
        }
    }
}
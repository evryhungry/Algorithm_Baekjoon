import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int M;
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static int[] degree = new int[32001];
    static Queue<Integer> q;
    BufferedReader br;
    StringTokenizer st;

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        q = new ArrayDeque<>();

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.get(a).add(b);
            degree[b]++;
        }

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) q.add(i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        init();

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int a = q.poll();
            sb.append(a).append(" ");

            for (int b : map.get(a)) {
                degree[b]--;
                if (degree[b] == 0) q.add(b);
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
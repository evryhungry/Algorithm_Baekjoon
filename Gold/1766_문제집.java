import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1766
public class Main {
    static int N;
    static int M;
    static List<Integer>[] graph;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

         graph = new ArrayList[N + 1];
         for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

         indegree = new int[N + 1];
         for (int i = 0; i < M; i++) {
             st = new StringTokenizer(br.readLine());
             int a = Integer.parseInt(st.nextToken());
             int b = Integer.parseInt(st.nextToken());

             graph[a].add(b);
             indegree[b]++;
         }


         PriorityQueue<Integer> pq = new PriorityQueue<>();
         for (int i = 1; i <= N; i++) {
             if (indegree[i] == 0) pq.add(i);
         }

         StringBuilder sb = new StringBuilder();
         while (!pq.isEmpty()) {
             int u = pq.poll();
             sb.append(u).append(" ");

             for (int v : graph[u]) {
                 indegree[v]--;
                 if (indegree[v] == 0) pq.add(v);
             }
         }

         bw.write(sb.toString());
         bw.flush();
         br.close();
    }
}
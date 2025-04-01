import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14938
public class Main {
    static int n, m, r;
    static int INF = Integer.MAX_VALUE;
    static Map<Integer, Integer> values = new HashMap<>();
    static List<List<Node>> nodes = new ArrayList<>();

    static class Node implements Comparable<Node>{
        int to;
        int distence;

        public Node(int to, int distence){
            this.to = to;
            this.distence = distence;
        }

        public int compareTo(Node o) {
            return this.distence - o.distence;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i <= n; i++) {
            if (i == 0){
                nodes.add(new ArrayList<>());
                continue;
            }
            int v = Integer.parseInt(st.nextToken());
            nodes.add(new ArrayList<>());
            values.put(i, v);
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            nodes.get(from).add(new Node(to, dist));
            nodes.get(to).add(new Node(from, dist));
        }

        int maxValue = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int[] dist = dijkstra(i);
            int total = 0;
            for (int j = 0; j <= n ; j++){
                if (dist[j] <=  m){
                    total += values.get(j);
                }
            }
            maxValue = Math.max(maxValue, total);
        }
        System.out.println(maxValue);
    }

    public static int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.to] < now.distence) continue;

            for (Node next : nodes.get(now.to)) {
                int cost = dist[now.to] + next.distence;
                if (cost < dist[next.to]) {
                    dist[next.to] = cost;
                    pq.offer(new Node(next.to, cost));
                }
            }
        }

        return dist;
    }
}
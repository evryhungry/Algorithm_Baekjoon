import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11779
class Node implements Comparable<Node> {
    int to;
    int cost;

    public Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return cost - o.cost;
    }
}


public class Main {
    static int n, m;
    static List<Node>[] list;
    static int[] dist;
    static boolean[] visited;
    static int[] route;
    static int INF = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        list = new List[n+1];
        visited = new boolean[n+1];
        dist = new int[n+1];
        route = new int[n+1];

        for (int i = 0; i < n+1; i++) {
            list[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for (int i = 0; i < m; i++) {
            String[] tokens = br.readLine().split(" ");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int value = Integer.parseInt(tokens[2]);

            list[from].add(new Node(to, value));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(dist[end]);
        ArrayList<Integer> routes = new ArrayList<>();
        int current = end;
        while(current != 0) {
            routes.add(current);
            current = route[current];
        }
        System.out.println(routes.size());
        for(int i = routes.size() - 1; i >= 0; i--) {
            System.out.print(routes.get(i) + " ");
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0));
        route[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int now = current.to;

            if (visited[now]) continue;
            visited[now] = true;

            for (Node next : list[now]) {
                if (dist[next.to] > dist[now] + next.cost) {
                    dist[next.to] = dist[now] + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                    route[next.to] = current.to;
                }
            }
        }
    }
}
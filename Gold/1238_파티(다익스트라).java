import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/1238

/**
 * Represents a load with an endpoint and a weight.
 * This class implements {@link Comparable} to enable sorting based on weight.
 */
class Load implements Comparable<Load> {
    Integer end;
    Integer weight;

    /**
     * Constructs a new {@code Load} with the specified endpoint and weight.
     *
     * @param end    the endpoint of the load
     * @param weight the weight of the load
     */
    Load(Integer end, Integer weight) {
        this.end = end;
        this.weight = weight;
    }


    /**
     * Compares this load to another based on weight.
     * A smaller weight is considered smaller in sorting.
     *
     * @param o the other {@code Load} to compare with
     * @return a negative integer if this load has a smaller weight,
     *         zero if weights are equal, or a positive integer if this load has a larger weight
     */
    @Override
    public int compareTo(Load o) {
        return weight - o.weight;
    }
}

public class Main {
    static int INF = Integer.MAX_VALUE;
    static int n, m, x;
    static ArrayList<ArrayList<Load>> Loads, reverse_Loads;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        Loads = new ArrayList<>();
        reverse_Loads = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            Loads.add(new ArrayList<>());
            reverse_Loads.add(new ArrayList<>());
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Loads.get(s).add(new Load(e, w));
            reverse_Loads.get(e).add(new Load(s, w));
        }

        int[] dist1 = dijkstra(Loads);
//        System.out.println(Arrays.toString(dist1));
        int[] dist2 = dijkstra(reverse_Loads);
//        System.out.println(Arrays.toString(dist2));
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist1[i] + dist2[i]);
        }

        System.out.println(ans);
    }

    /**
     * Implements Dijkstra's algorithm to find the shortest path from a source node.
     *
     * @param a an adjacency list where each node contains a list of {@code Load} objects representing edges
     * @return an array where each index represents the shortest distance from the source node to that index
     */
    public static int[] dijkstra(ArrayList<ArrayList<Load>> a) {
        PriorityQueue<Load> pq = new PriorityQueue<>();
        pq.offer(new Load(x, 0));

        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[x] = 0; // 초기설정 중요!

        while (!pq.isEmpty()) {
            Load curload = pq.poll();
            int cur = curload.end;

            if (!visited[cur]) {
                visited[cur] = true;

                for (Load load : a.get(cur)) {
                    if(!visited[load.end] && dist[load.end] > load.weight + dist[cur]) {
                        dist[load.end] = load.weight + dist[cur];
                        pq.offer(new Load(load.end, dist[load.end]));
                    }
                }
            }
        }
        return dist;
    }
}
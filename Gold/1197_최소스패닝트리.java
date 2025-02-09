import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// https://www.acmicpc.net/problem/1197
public class Main {
    static int total, N, M;
    static List<Node>[] list;
    static boolean[] visited;
    static Queue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        prim(1);
        System.out.println(total);
    }

    static void prim(int start) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));

        while(!pq.isEmpty()) {
            Node p = pq.poll();
            int node = p.to;
            int weight = p.value;

            if(visited[node]) continue;
            visited[node]= true;
            total += weight;

            for(Node next : list[node]) {
                if(!visited[next.to]) {
                    pq.add(next);
                }
            }
        }

    }
}

class Node implements Comparable<Node>{
    int to;
    int value;

    public Node(int to, int value) {
        this.to = to;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}


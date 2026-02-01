import java.io.*;
import java.util.StringTokenizer;


// https://www.acmicpc.net/problem/20040
public class Main {
    static class Route {
        int from;
        int to;

        Route(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    static int N;
    static int M;
    static Route[] routes;

    static int[] parent;
    static int[] rank;

    static int find(int x) {
        if (parent[x] == x) return x;
        parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return false; // already connected -> cycle if you add this edge

        // union by rank
        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }
        return true;
    }

    static int solve(Route[] routes) {

        for (int i = 0; i < M; i++) {
            int a = routes[i].from;
            int b = routes[i].to;

            // if union fails, cycle formed at turn i+1
            if (!union(a, b)) return i + 1;
        }
        return 0; // no cycle
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        routes = new Route[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            routes[i] = new Route(from, to);
        }

        int ans = solve(routes);
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
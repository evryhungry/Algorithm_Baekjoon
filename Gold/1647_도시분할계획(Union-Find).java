import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/15681
public class Main {
    static class Node implements Comparable<Node> {
        int start, end;
        int weight;

        public Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    static int n ;
    static int m ;
    static int[] parent;
    static ArrayList<Node> nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodes = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodes.add(new Node(start, end, weight));
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        Collections.sort(nodes);

        int cost = 0;
        int biggest = 0;
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            if(findCircle(n.start) != findCircle(n.end)) {
                cost += n.weight;
                union(n.start, n.end);
                biggest = n.weight;
            }
        }

        bw.write((cost - biggest) + "\n");
        bw.flush();
        bw.close();
    }

    // 같은 집합인지 확인하는 함수.
    static int findCircle(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = findCircle(parent[x]);
    }

    static void union(int start, int end) {
        start = findCircle(start);
        end = findCircle(end);

        if (start != end) {
            parent[end] = start;
        }
    }
}
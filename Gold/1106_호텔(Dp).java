import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1106
public class Main {
    static class Node {
        int costs;
        int people;

        Node(int c, int p){
            this.costs = c;
            this.people = p;
        }
    };

    static int n;
    static int C;
    static List<Node> nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            nodes.add(new Node(cost, people));
        }

        int maxCost = C * 100;
        int[] dp = new int[maxCost + 1];
        Arrays.fill(dp, 0);
        for (int i = 0; i <= maxCost; i++) {
            for (Node node : nodes) {
                if (i >= node.costs) {
                    dp[i] = Math.max(dp[i], dp[i - node.costs] + node.people);
                }
            }
        }

        for (int i = 0; i <= maxCost; i++) {
            if (dp[i] >= C) {
                bw.write(i + "\n");
                break;
            }
        }

        bw.flush();
        br.close();
    }
}
import java.io.*;
import java.util.*;

class Road {
    int end;
    int weight;

    Road(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}

// https://www.acmicpc.net/problem/1865
public class Main {
    static int t, n, m, w;
    static int[] dist;
    static ArrayList<ArrayList<Road>> a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            a = new ArrayList<>();
            dist = new int[n+1];
            for (int j = 0; j <= n; j++) {
                a.add(new ArrayList<>());
            }

            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                a.get(start).add(new Road(end, weight));
                a.get(end).add(new Road(start, weight));
            }

            for (int j = 0; j < w; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                a.get(start).add(new Road(end, -weight));
            }

            sb.append(bellmanFord() ? "NO" : "YES").append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static boolean bellmanFord(){
        Arrays.fill(dist, 0); // 초기시작이 여러곳일 수 있다.

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Road r : a.get(j)) {
                    if (dist[j] + r.weight < dist[r.end]) {
                        dist[r.end] = dist[j] + r.weight;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (Road r : a.get(i)) {
                if (dist[i] + r.weight < dist[r.end]) {
                    return false;
                }
            }
        }
        return true;

    }
}

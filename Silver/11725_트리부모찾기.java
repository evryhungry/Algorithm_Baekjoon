import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11725
public class Main {
    static int n;
    static int[] parents;
    static boolean[] visited;
    static ArrayList<Integer> list[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        parents = new int[n + 1];
        visited = new boolean[n + 1];
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        dfs(1);

        for (int i = 2; i <= n; i++) {
            bw.write(parents[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    static void dfs(int v){
        visited[v] = true;

        for(int i : list[v]){
            if(!visited[i]){
                parents[i] = v;
                dfs(i);
            }
        }
    }
}

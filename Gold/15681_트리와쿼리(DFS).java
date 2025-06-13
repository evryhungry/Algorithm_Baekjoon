import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/15681
public class Main {
    static int N;
    static int R;
    static int Q;
    static int[] qArray, cnt;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        cnt = new int[N + 1];

        for(int i = 0; i <= N ; i++) tree[i] = new ArrayList<>();
        for(int i = 1; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        qArray = new int[Q];
        for (int i = 0; i < Q ; i++) qArray[i] = Integer.parseInt(br.readLine());

        dfs(R, 0);
        StringBuilder sb = new StringBuilder();
        for (int i : qArray) {
            sb.append(cnt[i] + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
    }

    static void dfs(int root, int parent){
        cnt[root] = 1;
        for(int i : tree[root]){
            if(i == parent) continue;
            dfs(i, root);
            cnt[root] += cnt[i];
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int n;
    static int[] next;
    static boolean[] visited, finished;
    static int cycleCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine().trim());
        while (t --> 0) {
            n = Integer.parseInt(br.readLine().trim());
            next = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            cycleCount = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                next[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) dfs(i);
            }

            sb.append(n - cycleCount).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int x) {
        visited[x] = true;
        int y = next[x];

        if (!visited[y]) {
            dfs(y);
        } else if (!finished[y]) {
            cycleCount++;
            for (int cur = y; cur != x; cur = next[cur]) {
                cycleCount++;
            }
        }

        finished[x] = true;
    }
}
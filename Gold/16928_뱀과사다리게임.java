import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/16928
public class Main {
    static int n, m;
    static int[] map = new int[101];
    static boolean[] visited = new boolean[101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[101];
        visited = new boolean[101];

        Arrays.fill(map, -1);

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())] =  Integer.parseInt(st.nextToken());
        }

        System.out.println(bfs(1, 0));

    }

    static int bfs(int x, int c){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, c});
        visited[x] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cur_location = cur[0];
            int dice_count = cur[1];

            for (int i = 1; i <= 6; i++) {
                int next_location = cur_location + i;

                if (next_location > 100) continue;

                if (map[next_location] != -1) {
                    next_location = map[next_location];
                }

                if (next_location == 100) {
                    return dice_count + 1;
                }

                if (!visited[next_location]) {
                    visited[next_location] = true;
                    q.add(new int[]{next_location, dice_count + 1});
                }

            }
        }

        return -1;
    }
}

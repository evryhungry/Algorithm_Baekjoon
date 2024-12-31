import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * 문제: 1005_ACMCraft
 */
public class Main {
    static int[] delay;
    static ArrayList<Integer>[] list;
    static int[] inDegree;
    static int[] result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCases; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            delay = new int[n + 1];
            list = new ArrayList[n + 1];
            inDegree = new int[n + 1];
            result = new int[n + 1];

            for (int l = 1; l <= n; l++) {
                list[l] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int l = 1; l <= n; l++) {
                delay[l] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list[x].add(y);
                inDegree[y]++;
            }

            int target = Integer.parseInt(br.readLine());

            topologySort(n, target);
        }
    }

    public static void topologySort(int n, int target){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
                result[i] = delay[i];
            }
        }

        while (!q.isEmpty()){
            int current = q.poll();

            for (int next : list[current]) {
                inDegree[next]--;
                result[next] = Math.max(result[next], result[current] + delay[next]);
                if (inDegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        System.out.println(result[target]);
    }
}

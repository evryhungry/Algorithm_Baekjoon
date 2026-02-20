import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main{
    private static int N ;
    private static int M ;
    private static ArrayList<ArrayList<Integer>> graph ;
    private static int[] isDegree ;


    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) { graph.add(new ArrayList<>()); }

        isDegree = new int[N+1];

        for (int i = 0; i < M; i++) {
            List<Integer> list = Arrays.stream(br.readLine().split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            for (int j = 1; j < list.get(0); j++) {
                int from = list.get(j);
                int to = list.get(j+1);
                graph.get(from).add(to);
                isDegree[to]++;
            }
        }

        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) { if (isDegree[i] == 0) queue.add(i); }

        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            ans.add(cur);
            for (int v : graph.get(cur)) {
                isDegree[v]--;
                if (isDegree[v] == 0) queue.add(v);
            }
        }

        if (ans.size() != N) {
            bw.write("0\n");
        } else {
            for (int x : ans) bw.write(x + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
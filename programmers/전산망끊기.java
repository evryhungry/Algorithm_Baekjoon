package programmers;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

/**
 * 1 번째 풀이 dfs / 20분
 *
 * 풀이:
 * 1.단 양방향 연결이라는 것을 알고,
 * 2.차이의 절대값을 원한다는 것 => 하나의 연결을 알면 반대편 갯수 알수 있어서 편리.
 * 3.dfs로 접근한 이유: 연결된 노드를 자르고 연결된 노드를 찾을떄 용이함.
 */
class Solution {
    static List<Integer>[] graph;
    static boolean[] visited;

    public int solution(int n, int[][] wires) {
        graph = new ArrayList[101];
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            graph[a].add(b);
            graph[b].add(a);
        }

        for (int[] wire : wires) {
            int cutA = wire[0];
            int cutB = wire[1];

            visited = new boolean[n + 1];
            int count = dfs(1, cutA, cutB);
            answer = Math.min(answer, Math.abs(2 * count - n));
        }

        return answer;
    }

    private int dfs(int current, int cutA, int cutB) {
        visited[current] = true;
        int count = 1;

        for (int next : graph[current]) {
            if (visited[next]) continue;

            if (current == cutA && next == cutB) continue;
            if (current == cutB && next == cutA) continue;

            count += dfs(next, cutA, cutB);
        }

        return count;
    }
}

/**
 * 2번째 bfs / 10분
 *
 * dfs를 풀고나서 푸니까 훨씬 쉬워짐.
 */

class Solution {
    static List<Integer>[] graph;
    static boolean[] visited;

    public int solution(int n, int[][] wires) {
        graph = new ArrayList[101];
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            graph[a].add(b);
            graph[b].add(a);
        }

        for (int[] wire : wires) {
            int cutA = wire[0];
            int cutB = wire[1];

            visited = new boolean[n + 1];
            int count = bfs(cutA, cutB);
            answer = Math.min(answer, Math.abs(2 * count - n));
        }

        return answer;
    }

    private int bfs(int cutA, int cutB){
        Queue<Integer> q = new ArrayDeque<>();

        q.add(cutA);
        visited[cutA] = true;
        visited[cutB] = true;

        int count = 0;
        while(!q.isEmpty()){
            int current = q.poll();
            count++;

            for (int next : graph[current]){
                if (visited[next]) continue;

                visited[next] = true;
                q.add(next);
            }
        }

        return count ;
    }
}
package programmers;

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

class Solution {
    private int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
    private boolean[][] visited;
    private int[][] int_map;
    private int r, c;

    private void init(String[] maps){
        visited = new boolean[r][c];
        int_map = new int[r][c];

        for (int nr = 0 ; nr < r ; nr++){
            String s = maps[nr];
            for (int nc = 0; nc < c ; nc++){
                char ch = s.charAt(nc);

                if (ch == 'X') {
                    visited[nr][nc] = true;
                    int_map[nr][nc] = -1;
                } else {
                    int_map[nr][nc] = ch - '0';
                }
            }
        }
    }

    public int[] solution(String[] maps) {
        r = maps.length;
        c = maps[0].length();
        Queue<Integer> pq = new PriorityQueue<>();

        init(maps);

        for (int i = 0 ; i < r ; i++){
            for (int j = 0 ; j < c ; j++){
                if(int_map[i][j] != -1 && !visited[i][j] ){
                    pq.add(bfs(i, j));
                }
            }
        }

        return pq.isEmpty() ? new int[]{-1} : pq.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

    private int bfs(int sr, int sc){
        Queue<int[]> q = new ArrayDeque<>();

        visited[sr][sc] = true;
        q.add(new int[]{sr, sc});

        int answer = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cr = cur[0], cc = cur[1];

            answer += int_map[cr][cc];

            for (int i = 0 ; i < 4 ; i++){
                int nr = cr + dr[i] ;
                int nc = cc + dc[i] ;

                if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue ;
                if(visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
            }
        }

        return answer;
    }
}

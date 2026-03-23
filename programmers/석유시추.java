import java.util.*;

class Solution {
    private int[] dc = {1, 0, -1, 0};
    private int[] dr = {0, 1, 0, -1};
    private int row, col;
    private boolean[][] visited;

    public int solution(int[][] land) {
        row = land.length;
        col = land[0].length;
        visited = new boolean[row][col];
        int[] colOil = new int[col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (land[r][c] == 1 && !visited[r][c]) {
                    bfs(land, r, c, colOil);
                }
            }
        }

        int answer = 0;
        for (int v : colOil) answer = Math.max(answer, v);
        return answer;
    }

    private void bfs(int[][] land, int sr, int sc, int[] colOil) {
        int size = 0;
        Set<Integer> cols = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();

        visited[sr][sc] = true;
        q.add(new int[]{sr, sc});

        while (!q.isEmpty()) {
            int[] n = q.poll();
            size++;
            cols.add(n[1]);

            for (int i = 0; i < 4; i++) {
                int nr = n[0] + dr[i];
                int nc = n[1] + dc[i];
                if (nr < 0 || nc < 0 || nr >= row || nc >= col) continue;
                if (visited[nr][nc] || land[nr][nc] == 0) continue;
                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
            }
        }

        for (int cc : cols) {
            colOil[cc] += size;
        }
    }
}
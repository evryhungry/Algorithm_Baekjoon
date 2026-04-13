package programmers;

import java.util.Queue;
import java.util.ArrayDeque;

// method 를 1개만 만들어도 괜찮았을 텐데.. 아쉽다.
class Solution {
    private int INF = 987654321;
    private int[][] string_maps;
    private int[][] l_map ;
    private int[][] d_map ;
    private Queue<int[]> s_to_l = new ArrayDeque<>();
    private Queue<int[]> l_to_d = new ArrayDeque<>();
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, -1, 0, 1};

    private int r_l = -1, c_l = -1;
    private int r_d = -1, c_d = -1;

    public int solution(String[] maps) {
        int column = maps[0].length();
        int row = maps.length;

        string_maps = new int[row][column];
        l_map = new int[row][column];
        d_map = new int[row][column];

        for (int r = 0 ; r < row ; r++){
            for (int c = 0 ; c < column ; c++){
                l_map[r][c] = INF;
                d_map[r][c] = INF;
                char nc = maps[r].charAt(c);

                switch (nc){
                    case 'X' -> {
                        string_maps[r][c] = -1;
                    }
                    case 'S' -> {
                        string_maps[r][c] = 1;
                        l_map[r][c] = 0;
                        s_to_l.add(new int[]{r, c});
                    }
                    case 'L' -> {
                        string_maps[r][c] = 2;
                        d_map[r][c] = 0;
                        r_l = r;  c_l = c;
                    }
                    case 'E' -> {
                        string_maps[r][c] = 3;
                        r_d = r; c_d = c; }
                    default -> string_maps[r][c] = 0;
                }
            }
        }

        searchLever(column, row);
        if (l_to_d.isEmpty()) return -1;
        searchDoor(column, row);
        if (d_map[r_d][c_d] == INF) return -1;

        return l_map[r_l][c_l] + d_map[r_d][c_d];
    }

    private void searchLever(int column, int row){
        while(!s_to_l.isEmpty()){
            int[] current = s_to_l.poll();
            int cr = current[0];
            int cc = current[1];

            for (int i = 0 ; i < 4 ; i++){
                int nr = cr + dy[i];
                int nc = cc + dx[i];

                if (nr < 0 || nr >= row || nc < 0 || nc >= column) continue ;
                if (string_maps[nr][nc] == -1) continue;

                if ((l_map[cr][cc] + 1) < l_map[nr][nc]){
                    l_map[nr][nc] = l_map[cr][cc] + 1;

                    if (string_maps[nr][nc] == 2){
                        l_to_d.add(new int[]{nr, nc});
                        return;
                    }
                    s_to_l.add(new int[]{nr, nc});
                }
            }
        }

        return ;
    }

    private void searchDoor(int column, int row){
        while(!l_to_d.isEmpty()){
            int[] current = l_to_d.poll();
            int cr = current[0];
            int cc = current[1];

            for (int i = 0 ; i < 4 ; i++){
                int nr = cr + dy[i];
                int nc = cc + dx[i];

                if (nr < 0 || nr >= row || nc < 0 || nc >= column) continue ;
                if (string_maps[nr][nc] == -1) continue;

                if ((d_map[cr][cc] + 1) < d_map[nr][nc]){
                    d_map[nr][nc] = d_map[cr][cc] + 1;

                    if (string_maps[nr][nc] == 3){
                        return;
                    }

                    l_to_d.add(new int[]{nr, nc});
                }
            }
        }

        return ;
    }
}

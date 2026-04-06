package programmers;

import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    private int INF = 98654321;
    private int N ;
    private int M ;
    private char[][] map ;
    private int[][] visited;
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, -1, 0, 1};
    private Deque<Node> q = new ArrayDeque<>();


    private void init(String[] board){
        N = board.length;
        M = board[0].length();
        map = new char[N][M];
        visited = new int[N][M];

        for (int n = 0; n < N ; n++){
            char[] tmp = board[n].toCharArray();
            for (int m = 0 ; m < M ; m++){
                map[n][m] = tmp[m];
                visited[n][m] = INF;

                if (tmp[m] == 'R'){
                    q.offer(new Node(n, m, 0));
                    visited[n][m] = 0;
                }
            }
        }

    }

    public int solution(String[] board) {
        init(board);

        while(!q.isEmpty()){
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int c = cur.c;

            if (map[x][y] == 'G') return c;

            int tmp = c+1;
            for (int i = 0 ; i < 4; i++){
                int nxt_x = x;
                int nxt_y = y;

                while (nxt_x + dx[i] >= 0 && nxt_x + dx[i] < N
                        && nxt_y + dy[i] >= 0 && nxt_y + dy[i] < M
                        && map[nxt_x + dx[i]][nxt_y + dy[i]] != 'D') {
                    nxt_x += dx[i];
                    nxt_y += dy[i];
                }


                if (tmp < visited[nxt_x][nxt_y]) {
                    visited[nxt_x][nxt_y] = tmp;
                    q.offer(new Node(nxt_x, nxt_y, tmp));
                }
            }
        }

        return -1;
    }

    class Node{
        int x;
        int y;
        int c;

        public Node(int x, int y, int c){
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
}
/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/388353
 * 지게차와 크레인
 * 푸는데 걸린 시간: 1h
 * 
 */
package programmers;

class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int x, y, ans;
    static char[][] map;
    static boolean[][] visited;

    public int solution(String[] storage, String[] requests) {
        x = storage.length;
        y = storage[0].length();
        ans = x * y;
        map = new char[x][y];

        for (int i = 0; i < x; i++) map[i] = storage[i].toCharArray();

        for(String req : requests){
            if (req.length() == 1){
                forkLift(req.charAt(0));
            } else {
                crane(req.charAt(0));
            }
        }

        return ans;
    }

    static void crane(char c){
        for (int i = 0 ; i < x; i++){
            for (int j = 0 ; j < y ; j++){
                if (map[i][j] == c){
                    map[i][j] = 0;
                    ans--;
                }
            }
        }
    }

    static void forkLift(char c) {
        visited = new boolean[x][y];
        for(int i = 0; i < x; i++) {
            if(!visited[i][0]) dfs(i, 0, c);
            if(!visited[i][y-1]) dfs(i, y-1, c);
        }
        for(int i = 0; i < y; i++) {
            if(!visited[0][i]) dfs(0, i, c);
            if(!visited[x-1][i]) dfs(x-1, i, c);
        }
    }

    static void dfs(int nx, int ny, char c){
        visited[nx][ny] = true;
        if (map[nx][ny] == 0){
            for(int i = 0 ; i < 4 ; i++){
                int lx = nx + dx[i];
                int ly = ny + dy[i];

                if(lx >= x || ly >= y || lx < 0 || ly < 0) continue;
                if(!visited[lx][ly]) dfs(lx, ly, c);
            }
        }

        if (map[nx][ny] == c){
            map[nx][ny] = 0;
            ans--;
        }
    }
}
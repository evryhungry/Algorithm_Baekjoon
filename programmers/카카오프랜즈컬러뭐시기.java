/**
 * 프로그래머스 - 카카오프렌즈 컬러링북
 * https://school.programmers.co.kr/learn/courses/30/lessons/1829
 * BFS
 * 문제해석:
 * 1. m x n 크기의 2차원 배열 picture
 * 2. 각 칸은 0 이상 2,147,483,647 이하의 정수로 색을 나타냄
 * 3. 0은 색이 없는 칸을 나타냄
 * 4. 같은 색이 인접한 칸은 하나의 영역을 이룸
 * 5. 인접한 칸은 상하좌우로 연결된 칸
 * 6. picture에 몇 개의 영역이 있는지, 가장 넓은 영역의 크기는 몇 칸인지
 * 7. 영역의 개수와 가장 넓은 영역의 크기를 배열에 담아 return
 *
 *
 * 풀이:
 * 1. BFS로 탐색
 * 2. visited 배열로 방문 체크
 * 3. 4방향 탐색
 * 4. 영역의 개수와 가장 넓은 영역의 크기 갱신
 *
 * 시간: O(m * n)
 * 공간: O(m * n)
 *
 * 푸는데 걸린 시간 : 40분
 */

import java.util.*;

class Solution {
    boolean[][] visited;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};


    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];
        int[] answer = new int[2];

        for (int x = 0 ; x < m ; x++){
            for (int y = 0 ; y < n ; y++){
                if (picture[x][y] != 0 && visited[x][y] == false){
                    int result = bfs(x, y, picture);
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(result, maxSizeOfOneArea);
                }
            }
        }

        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int bfs(int x, int y, int[][] picture){
        int m = picture.length;
        int n = picture[0].length;

        Queue<int[]> q = new ArrayDeque<int[]>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        int color = picture[x][y];
        int size = 1;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for (int dir = 0 ; dir < 4 ; dir++){
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if (visited[nx][ny] || picture[nx][ny] != color) continue;

                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                size++;
            }
        }

        return size;
    }
}
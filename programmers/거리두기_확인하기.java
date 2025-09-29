/**
 * 거리두기 확인하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/81302
 * 2021 KAKAO BLIND RECRUITMENT
 * 41분
 *
 * 풀이 :
 * 1. places를 순회하면서 각 대기실을 확인
 * 2. 대기실을 5x5 격자로 순회하면서 'P'를 찾으면 bfs로 거리두기 확인
 * 3. bfs는 큐를 사용해서 현재 위치에서 상하좌우로 이동하면서 거리두기 확인
 * 4. 이동한 위치가 'P'이고 맨해튼 거리가 2 이하이면 거리두기 실패
 * 5. 이동한 위치가 'O'이고 맨해튼 거리가 1 이하이면 큐에 추가
 * 6. 모든 위치를 확인했는데 거리 두기 실패하지 않으면 거리두기 성공
 * 7. 시간복잡도 O(n^2), 공간복잡도 O(n)
 */

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            boolean ok = true;
            String[] p = places[i];

            out:
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (p[r].charAt(c) == 'P') {
                        if (bfs(r, c, p)) {
                            ok = false;
                            break out;
                        }
                    }
                }
            }

            answer[i] = ok ? 1 : 0;
        }
        return answer;
    }

    private static boolean bfs(int r, int c, String[] p){
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[]{r, c});

        while(!q.isEmpty()){
            int[] position = q.poll();

            for (int i = 0 ; i < 4 ; i ++){
                int nowR = position[0] + dr[i];
                int nowC = position[1] + dc[i];

                if (nowR < 0 || nowC < 0 || nowR >= 5 || nowC >= 5 || (nowR == r && nowC == c)) continue;
                int d = Math.abs(nowR - r) + Math.abs(nowC - c);

                if (d <= 2 && p[nowR].charAt(nowC) == 'P') return true;
                else if (d < 2 && p[nowR].charAt(nowC) == 'O') q.offer(new int[]{nowR, nowC});
            }
        }

        return false;
    }
}
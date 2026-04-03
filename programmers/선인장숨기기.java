package programmers;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/468379 # 선인장 숨기기
class Solution {
    private int[][] map;

    /*
        map[x][y] = i : (x, y) 위치에 i번째 물방울이 떨어진다는 의미.
        Integer.MAX_VALUE : 해당 칸에는 물방울이 떨어지지 않음.

        이렇게 저장하는 이유:
        이분탐색의 mid 값과 비교할 때, map[r][c] <= mid 이면
        "mid번째 시점 이전(또는 동시)에 이미 비를 맞은 칸"으로 판별 가능.
     */
    private void init(int m, int n, int[][] drops){
        map = new int[m][n];

        for (int[] row : map) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 1 ; i <= drops.length ; i++){
            int x = drops[i-1][0]; int y = drops[i-1][1];
            map[x][y] = i;
        }
    }

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        init(m, n, drops);
        int[] answer = {};

        int left = 0 ; int right = drops.length;

        // 이분 탐색: 버틸 수 있는 최대 물방울 시간를 찾음
        while(left <= right){
            int mid = (left + right) / 2;
            int[] pos = check_safety_form_rain(m, n, h, w, mid);

            if (pos != null){
                answer = pos;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    /*
        "처음 tmp개의 물방울이 떨어졌을 때, 비를 하나도 안 맞는 h×w 영역이 있는가?"

        [1단계] 2차원 누적합(Prefix Sum) 구성
        - map[r][c] <= tmp 이면 1 (= 이미 비를 맞은 칸)
        - map[r][c] >  tmp 이면 0 (= 아직 안전한 칸)
        - posSum[r+1][c+1]에 (0,0)~(r,c) 영역의 "비 맞은 칸 수"를 누적.
        - posSum을 (m+1)×(n+1)로 잡는 이유: 0번 행/열을 패딩으로 두어
          경계 처리 없이 누적합 공식을 균일하게 적용하기 위함.

        [2단계] 슬라이딩 윈도우로 h×w 영역 전수 검사
        - 좌상단이 (r-h, c-w)이고 우하단이 (r-1, c-1)인 h×w 영역에 대해
          2차원 누적합의 부분합 공식:
            total = posSum[r][c] - posSum[r-h][c] - posSum[r][c-w] + posSum[r-h][c-w]
        - total == 0 이면 해당 영역 안에 비 맞은 칸이 하나도 없음 → 안전한 위치 발견.
        - 가장 먼저 발견된 위치의 좌상단 좌표 (r-h, c-w)를 반환.
        - 모든 영역에 비가 있으면 null 반환 → 이분탐색에서 right를 줄임.
     */
    private int[] check_safety_form_rain(int m, int n, int h, int w, int tmp){
        int[][] posSum = new int[m+1][n+1];

        for(int r = 0 ; r < m ; r++){
            for (int c = 0 ; c < n ; c++){
                int value = (map[r][c] <= tmp) ? 1 : 0;
                posSum[r + 1][c + 1] = value + posSum[r][c + 1] + posSum[r + 1][c] - posSum[r][c];
            }
        }

        for(int r = h ; r <= m ; r++){
            for(int c = w ; c <= n ; c++){
                int total = posSum[r][c] - posSum[r-h][c] - posSum[r][c-w] + posSum[r-h][c-w];
                if (total == 0) return new int[]{r - h, c - w};
            }
        }
        return null;
    }

}

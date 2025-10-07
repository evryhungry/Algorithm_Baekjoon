/**
 * 완전 범죄
 * https://school.programmers.co.kr/learn/courses/30/lessons/389480
 *
 * 문제해석:
 * 1. A형 범죄자와 B형 범죄자가 있다.
 * 2. A형 범죄자는 n의 흔적을 넘기면 채포된다.
 * 3. B형 범죄자는 m의 흔적을 넘기면 채포된다.
 * 4. A형 범죄자의 흔적은 info[i][0]에 담겨있고, B형 범죄자의 흔적은 info[i][1]에 담겨있다.
 * 5. 범죄자들이 체포를 피하고 싶다면, A형 범죄자는 n미만의 흔적을 남겨야 하고,
 *    B형 범죄자는 m미만의 흔적을 남겨야 한다.
 * 6. 범죄자들이 각각 체포를 피했을 때, A형 범죄자가 남긴 흔적의 합의 최솟값을 구하라.
 *
 * 문제 해결 방법:
 * 	•	모든 값을 INF로 채운다(도달 불가 상태).
 * 	•	첫 사람 i=0에 대해:
 * 	•	dp[0][0] = info[0][0] : 0번을 B형에 포함하지 않음 → B흔적 합 0, A흔적은 해당 사람(A)의 흔적만 더해짐
 * 	•	if (info[0][1] < m) dp[0][info[0][1]] = 0 : 0번을 B형에 포함 → B흔적 합이 info[0][1], A흔적을 0으로 둬서 “B로 처리하면 A는 부담 없음”의 기준점을 만든다
 * 	•	(이 초기화는 “해당 사람을 B에게 배정하면 A는 안 쌓인다”는 전이의 관점 기준점)
 *
 * 	전이(Transition)
 *    i번째 사람을 A로 보낼지(B에 안 넣을지), B로 보낼지 두 경우:
 *    1. B 흔적 합 j 유지, A 흔적 합에 info[i][0]을 추가 : dp[i][j] = min(dp[i][j], dp[i-1][j] + info[i][0]);
 *    2. B 흔적 합을 j → j + info[i][1]로 올리고, A 흔적 합은 증가시키지 않음(A 부담 없음)
 *          : if (j + info[i][1] < m) dp[i][j + info[i][1]] = min(dp[i][j + info[i][1]], dp[i-1][j]);
 *
 *  걸린 시간 : 1h
 */

import java.util.Arrays;

class Solution {
    public int solution(int[][] info, int n, int m) {
        if (info.length == 1) return info[0][1] < m ? 0 : info[0][0];

        int[][] dp = new int[info.length][m];
        int INF = 120;
        for (int i = 0 ; i < info.length ; i++) Arrays.fill(dp[i], INF);

        dp[0][0] = info[0][0];
        if (info[0][1] < m) {
            dp[0][info[0][1]] = 0;
        }

        for (int i = 1 ; i < info.length ; i++){
            for (int j = 0 ; j < m ; j++){
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + info[i][0]);
                if (j + info[i][1] < m){
                    dp[i][j+info[i][1]] = Math.min(dp[i][j+info[i][1]], dp[i-1][j]);
                }
            }
        }

        int answer = INF;
        for (int j = 0; j < m; j++) {
            answer = Math.min(answer, dp[info.length-1][j]);
        }
        return (answer < n) ? answer : -1;
    }
}
/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/150369
 * 택배 배달과 수거하기
 * 21분
 *
 * 문제해석:
 * 1. 트럭은 한 번에 cap 만큼의 물건을 싣고 배달하거나 수거할 수 있다.
 * 2. deliveries는 각 집에 배달해야 하는 물건의 수를 나타낸다.
 * 3. pickups는 각 집에서 수거해야 하는 물건의 수를 나타낸다.
 * 4. 트럭은 1번 집에서 출발하여 n번 집까지 이동한다.
 * 5. 트럭이 모든 배달과 수거를 완료하고 다시 1번 집으로 돌아오는 데 필요한 최소 이동 거리를 구하라.
 *
 * 문제 해결 방법:
 * 1. 가장 먼 집부터 시작하여 배달과 수거를 동시에 고려한다.
 * 2. 각 집에 대해 배달과 수거해야 할 물건의 수를 누적한다.
 * 3. 누적된 배달 또는 수거 물건이 0보다 크면, 트럭이 해당 집까지 왕복해야 한다.
 * 4. 트럭이 한 번에 cap 만큼의 물건을 싣고 배달하거나 수거할 수 있으므로, 누적된 물건의 수를 cap 만큼씩 줄여가며 왕복 거리를 계산한다.
 * 5. 모든 집을 처리할 때까지 이 과정을 반복한다.
 * 6. 최종적으로 계산된 이동 거리가 답이 된다.
 *
 * 시간 복합도: O(n)
 * 공간 복잡도: O(1)
 */
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0L;
        int d = 0;
        int p = 0;

        for(int i = n-1 ; i >= 0 ; i--){
            d += deliveries[i];
            p += pickups[i];

            while(d > 0 || p > 0){
                d -= cap;
                p -= cap;

                answer += ( i + 1 ) * 2;
            }
        }

        return answer;
    }
}
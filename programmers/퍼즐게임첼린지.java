/**
 * 퍼즐 게임 첼린지
 * https://school.programmers.co.kr/learn/courses/30/lessons/340212
 * 문제해석:
 * 1. 퍼즐 조각의 난이도(diffs)와 각 조각을 맞추는 데 걸리는 시간(times)이 주어짐
 * 2. 제한 시간(limit) 내에 모든 퍼즐 조각을 맞출 수 있는지 확인
 * 3. 모든 퍼즐 조각을 맞추기 위한 난이도의 최소값을 찾는 문제
 *
 * 풀이 방법:
 * 1. 이진 탐색을 사용하여 난이도의 최소값을 찾음
 * 2. mid 값을 기준으로 모든 퍼즐 조각을 맞출 수 있는지 확인
 * 3. 가능한 경우 난이도의 상한을 낮추고, 불가능한 경우 난이도의 하한을 높임
 * 4. 최종적으로 찾은 난이도의 최소값을 반환
 *
 * 시간 복잡도: O(n log m) (n: 퍼즐 조각의 수, m: 난이도의 범위)
 * 공간 복잡도: O(1)
 *
 * 푸는데 걸린 시간: 40분
 * 고찰 : 이진 탐색을 아직 내가 어려워하는 것이라고 생각해서 더 많은 문제르 풀어봐야겠다고 생각한다.
 */

package programmers;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        long left = 1;
        long right = limit;

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (isPossible(diffs, times, mid, limit)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return (int) left;
    }

    static boolean isPossible(int[] diffs, int[] times, long level, long limit){
        long t = (long)times[0];

        for(int i = 1; i < times.length; i++){
            if (limit < t) return false;

            if(diffs[i] > level){
                t += ((long)diffs[i] - level) * ((long)times[i-1] + (long)times[i]);
            }
            t += (long)times[i];
        }

        return limit >= t;
    }
}

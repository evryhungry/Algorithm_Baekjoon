/**
 * 덧칠하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/161
 *  문제해석:
 *  1. 길이가 n인 벽이 있음
 *  2. 벽의 특정 구간에 덧칠을 해야함
 *  3. 덧칠을 해야하는 구간은 section 배열에 담겨있다.
 *  4. 룰러의 길이는 m이다.
 *
 *  문제 해결방법:
 *  1. 덧칠해야 하는 부분부터 시작해서 m만큼 덧칠을 한다.
 *  2. 덧칠이 끝난 부분을 painted 변수에 저장한다.
 *  3. 다음 덧칠해야 하는 부분이 painted 변수보다 크다면
 *     다시 m만큼 덧칠을 하고 painted 변수를 갱신한다.
 *  4. 덧칠 횟수를 count 변수에 저장한다.
 */
class Solution {
    public int solution(int n, int m, int[] section) {
        int count = 0;
        int painted = 0;

        for (int s : section) {
            if (s > painted) {
                painted = s + m - 1;
                count++;
            }
        }

        return count;
    }
}
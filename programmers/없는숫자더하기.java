/**
 * 없는 숫자 더하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/86051
 * 문제해석:
 * 1. 0부터 9까지의 숫자 중 일부가 들어있는 배열 numbers가 주어짐
 * 2. numbers에 없는 0~9까지의 숫자를 모두 찾아 더한 값을 return
 * 문제 해결방법:
 * 1. 0~9까지의 숫자의 합은 45
 * 2. numbers 배열에 있는 숫자들의 합을 구함
 * 3. 45에서 numbers 배열의 합을 뺌
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(1)
 *
 * 풀이 시간: 1분
 */

package programmers;

class Solution {
    public int solution(int[] numbers) {
        int sum_num = 0;
        for (int n : numbers) sum_num += n;
        return 45 - sum_num;
    }
}

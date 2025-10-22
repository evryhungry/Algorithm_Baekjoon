/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/132267
 * 콜라문제
 * 걸린 시간 : 5분
 *
 */
package programmers;

class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        int temp = 0;

        while (n >= a) {
            temp = (n / a) * b;
            answer += temp;
            n = temp + n % a;
        }

        return answer;
    }
}

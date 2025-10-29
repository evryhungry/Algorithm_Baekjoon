/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/87389
 * 나머지가 1이 되는 수 찾기
 * 푸는데 걸린 시간 : 2분
 */
package programmers;

class Solution {
    public int solution(int n) {
        for (int i = 2 ; i < n ; i++){
            if (n % i == 1) return i;
        }

        return -1;
    }
}

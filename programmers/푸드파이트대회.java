/**
 * 푸드 파이트 대회
 * https://school.programmers.co.kr/learn/courses/30/lessons/134240
 * 시간 : 15분
 * 시간 복잡도 : O(n)
 */
package programmers;

class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i < food.length; i++){
            int cnt = food[i] / 2;
            sb.append(String.valueOf(i).repeat(cnt));
        }

        String answer = sb + "0";
        answer += sb.reverse();
        return answer;
    }
}

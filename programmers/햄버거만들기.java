/**
 * 햄버거 만들기
 * https://school.programmers.co.kr/learn/courses/30/lessons/133502
 * 걸린 시간 : 30분
 */
package programmers;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ingredient.length; i++) {
            sb.append(ingredient[i]);
            if (sb.length() > 3 && sb.substring(sb.length()-4, sb.length()).equals("1231")) {
                answer++;
                sb.delete(sb.length()-4, sb.length());
            }
        }

        return answer;
    }
}

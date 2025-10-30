/**
 * 숫자 짝꿍
 * https://school.programmers.co.kr/learn/courses/30/lessons/131128
 * 푸는데 걸린 시간: 20m
 * 문제풀이
 * 1. 각 문자열에 등장하는 숫자의 개수를 세기 위해 길이가 10인 배열을 사용
 * 2. 두 배열을 비교하여 공통으로 등장하는 숫자의 개수를 찾아 StringBuilder에 추가
 * 3. 결과 문자열이 비어있으면 "-1" 반환, 모두 '0'이면 "0" 반환, 그 외에는 결과 문자열 반환
 *
 * 아스키코드 0~9 : 48~57
 */
package programmers;

class Solution {
    public String solution(String X, String Y) {
        StringBuilder sb = new StringBuilder();
        int[] x = {0,0,0,0,0,0,0,0,0,0};
        int[] y = {0,0,0,0,0,0,0,0,0,0};

        for (int i = 0 ; i < X.length(); i++){
            x[X.charAt(i)-48]++;
        }
        for (int i = 0 ; i < Y.length(); i++){
            y[Y.charAt(i)-48]++;
        }

        for (int i = 9 ; i >= 0 ; i--){
            for (int j = 0 ; j < Math.min(x[i], y[i]); j++) sb.append(i);
        }

        String answer = sb.toString();

        if (answer.isEmpty()) return "-1";
        else if (answer.charAt(0) == '0') return "0";
        return answer;
    }
}
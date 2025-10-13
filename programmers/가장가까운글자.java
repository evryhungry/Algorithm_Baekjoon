/**
 * 가장 가까운 글자
 * 문제 설명
 * 문자열 s가 주어졌을 때, 각 글자에 대해서 자신과 같은 글
 * 자가 존재하는 경우, 자신과 가장 가까운 글자와의 거리를,
 * 존재하지 않는 경우 -1을 담은 배열을 return 하는 solution 함수를 완성해 주세요.
 *
 * 제한사항
 * 1 ≤ s의 길이 ≤ 10,000
 * s는 알파벳 소문자로만 이루어져 있습니다.
 *
 * 풀이
 * 1. 이중 for문을 사용하여 각 글자에 대해 이전 글자들을 탐색
 * 2. 같은 글자를 찾으면 거리를 계산하여 answer 배열에 저장하고 내부 for문 종료
 * 3. 내부 for문이 끝난 후에도 거리가 0이면 -1로 설정
 * 4. 최종적으로 answer 배열 반환
 *
 * 시간: 5분
 *
 * 그냥 빠르게 불려고 하다보니 이중 for문으로 품
 * 10,000^2 = 100,000,000 -> 1억
 * 1초에 1억번 연산 가능하다고 하니깐 통과된다고 생각. (효율성을 생각하면 이렇게 풀면 큰일남 ...)
 */
class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];

        for (int i = 0; i < s.length() ; i++){
            for (int j = i-1; j >= 0 ; j--){
                if ((s.charAt(i) == s.charAt(j))){
                    answer[i] = i - j;
                    break;
                }
            }

            if (answer[i] == 0) answer[i] = -1;
        }
        return answer;
    }
}
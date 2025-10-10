/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/118666
 * 성격 유형 검사하기
 * 1시간
 * 문제 설명:
 * 1. 성격 유형은 4가지 지표로 구분된다.
 * 2. 각 지표는 두 가지 성격으로 이루어져 있다.
 * 3. 각 지표는 아래와 같다.
 *    1) R(라이언형) - T(튜브형)
 *    2) C(콘형) - F(프로도형)
 *    3) J(제이지형) - M(무지형)
 *    4) A(어피치형) - N(네오형)
 * 4. 성격 유형 검사는 총 n개의 질문으로 이루어져 있다.
 * 5. 각 질문은 7개의 선택지로 이루어져 있다.
 * 6. 각 선택지는 아래와 같다.
 *    1) 매우 비동의
 *    2) 비동의
 *    3) 약간 비동의
 *    4) 모르겠음
 *    5) 약간 동의
 *    6) 동의
 *    7) 매우 동의
 *
 *  풀이 방법:
 *  1. 각 질문의 선택지에 따라 점수를 부여한다.
 *     1) 매우 비동의 : 3점
 *     2) 비동의 : 2점
 *     3) 약간 비동의 : 1점
 *     4) 모르겠음 : 0점
 *     5) 약간 동의 : 1점
 *     6) 동의 : 2점
 *     7) 매우 동의 : 3점
 *  2. 각 지표별로 점수를 합산한다.
 *  3. 각 지표별로 점수를 비교한다.
 *  4. 점수가 같다면 사전순으로 빠른 성격을 선택한다.
 *  5. 최종적으로 각 지표별로 선택된 성격을 이어붙여서 return 한다.
 *
 *  고찰:
 *  1. 디버깅 하는데 오래 걸렸다.
 *  2. 계산식으로 풀려다 보니까 더 오래걸리더라
 *  3. 그냥 리스트로 풀었으면 더 빨랐을 것 같다.
 *  4. 다음에는 더 쉽게 생각하고 풀자.
 */
class Solution {
    public String solution(String[] survey, int[] choices) {
        int[] ans = new int[4];
        String rcjf = "RCJA";
        String tfmn = "TFMN";

        for (int i = 0; i < survey.length; i++){
            String sur1 = String.valueOf(survey[i].charAt(0));
            String sur2 = String.valueOf(survey[i].charAt(1));
            int choice = choices[i];
            if (choice == 4) continue;

            int quo = choice / 4;
            int mod = choice % 4;
            int a;

            if (rcjf.contains(sur1)) {
                a = (quo != 0) ?  -mod : 4 - mod;
                ans[rcjf.indexOf(sur1)] += a;
            } else {
                a = (quo != 0) ? mod : mod - 4;
                ans[rcjf.indexOf(sur2)] += a;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++) sb.append((ans[i] < 0) ? tfmn.charAt(i) : rcjf.charAt(i));
        return sb.toString();
    }
}
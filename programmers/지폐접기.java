/*
1240 - 1249
문제 설명
1. 지폐를 접은 횟수를 저장할 정수 변수 answer를 만들고 0을 저장합니다.
2. 반복문을 이용해 bill의 작은 값이 wallet의 작은 값 보다 크거나 bill의 큰 값이 wallet의 큰 값 보다 큰 동안 아래 과정을 반복합니다.
    2-1. bill[0]이 bill[1]보다 크다면
        bill[0]을 2로 나누고 나머지는 버립니다.
    2-2. 그렇지 않다면
        bill[1]을 2로 나누고 나머지는 버립니다.
    2-3. answer을 1 증가시킵니다.
3. answer을 return합니다.
    위의 의사코드와 작동방식이 다른 코드를 작성해도 상관없습니다.

제한사항
    wallet의 길이 = bill의 길이 = 2
    10 ≤ wallet[0], wallet[1] ≤ 100
    10 ≤ bill[0], bill[1] ≤ 2,000

입출력 예
    wallet 	bill 	result
    [50, 50] 	[100, 241] 	4
입출력 예 설명
    지폐를 접으면 다음과 같이 크기가 줄어듭니다. 따라서 4번 접으면 지갑에 넣을 수 있습니다.
    [100, 241] -> [100, 120] -> [100, 60] -> [50, 60] -> [50, 30]

    cpp를 응시하는 경우 리스트는 배열과 동일한 의미이니 풀이에 참고해주세요.
        ex) 번호가 담긴 정수 리스트 numbers가 주어집니다. => 번호가 담긴 정수 배열 numbers가 주어집니다.
    java를 응시하는 경우 리스트는 배열, 함수는 메소드와 동일한 의미이니 풀이에 참고해주세요.
        ex) solution 함수가 올바르게 작동하도록 한 줄을 수정해 주세요. => solution 메소드가 올바르게 작동하도록 한 줄을 수정해 주세요.
 */

import java.lang.Math;

class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int w_max = Math.max(wallet[0], wallet[1]);
        int w_min = Math.min(wallet[0], wallet[1]);
        int b_max = Math.max(bill[0], bill[1]);
        int b_min = Math.min(bill[0], bill[1]);

        while (true){
            if(w_min < b_min || w_max < b_max){
                b_max /= 2;
                answer++;
            }

            if(b_max < b_min){
                int temp = b_max;
                b_max = b_min;
                b_min = temp;
            }

            if (w_min >= b_min && w_max >= b_max) break;
        }

        return answer;
    }
}
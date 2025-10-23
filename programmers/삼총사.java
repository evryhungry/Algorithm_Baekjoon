/**
 * 삼총사
 * https://school.programmers.co.kr/learn/courses/30/lessons/131705
 * 시간 : 3분
 */
package programmers;

class Solution {
    public int solution(int[] number) {
        int length = number.length;
        int answer = 0;

        for(int i = 0 ; i < length - 2; i++ ){
            for(int j = i+1 ; j < length - 1 ; j++){
                for (int k = j+1 ; k < length ; k++){
                    if (number[i] + number[j] + number [k] == 0) answer++;
                }
            }
        }
        return answer;
    }
}
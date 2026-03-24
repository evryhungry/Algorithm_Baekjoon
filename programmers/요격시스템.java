package programmers;

import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {

        Arrays.sort(targets,(o1,o2) -> o1[1]-o2[1]); // 각 행의 두 번째 원소를 기준으로 오름차순 정렬

        int end = 0; int answer = 0 ;

        for (int[] s : targets){
            if (s[0] >= end){
                end = s[1];
                answer++;
            }
        }

        return answer;
    }
}
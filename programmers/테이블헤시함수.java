package programmers;

import java.util.Arrays;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] != b[col - 1]) return a[col - 1] - b[col - 1]; // col을 기준으로 오름차순
            else return b[0] - a[0]; // 같으면 내림차순..
        });

        int answer = 0;
        for (int i = row_begin - 1;  i < row_end ; i++){ // 어차피 해당 row의 범위값만 알면 되니까
            int s = 0 ;

            for (int val : data[i]){
                s += val % (i + 1); // mod 개산후 추가.
            }

            answer ^= s; // 이전의 값과 XOR 확인
        }
        return answer;
    }
}
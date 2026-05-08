package programmers;

class Solution {
    public int solution(int storey) {
        int answer = 0;

        while(storey > 0){
            int mod = storey % 10;
            storey = storey / 10;

            if( mod > 5 ) {
                storey++;
                answer += 10 - mod;
            } else if (mod == 5 && (storey % 10) >= 5) {
                storey++;
                answer += 5;
            } else {
                answer += mod;
            }
        }

        return answer;
    }
}
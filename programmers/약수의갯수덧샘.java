class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        for(int s = left ; s <= right ; s++) {
            int cnt = count_divisors(s);
            if (cnt % 2 == 0) answer += s;
            else answer -= s;
        }
        return answer;
    }

    private int count_divisors(int s){
        int cnt = 0;
        for (int i = 1 ; i < Math.sqrt(s)+1 ; i++){
            if (s % i == 0){
                if (i * i == s) cnt++;
                else cnt += 2;
            }
        }
        return cnt;
    }
}
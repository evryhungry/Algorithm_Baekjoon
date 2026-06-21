class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        double d_max = Math.pow(d, 2);
        
        for (int i = 0 ; i <= d ; i += k){
            
            double max_y = Math.sqrt(d_max - (double)i*i);
            
            answer += (long) max_y / k + 1;
        }
        
        return answer;
    }
}
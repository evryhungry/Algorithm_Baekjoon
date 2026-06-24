class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for(int i = 0 ; i < numbers.length ; i++){
            if (numbers[i] % 2 == 0) answer[i] = numbers[i] + 1;
            else {
                long next = numbers[i];
                int count = 0;
                
                while(next % 2 == 1){
                    next /= 2;
                    count++;
                }
                
                answer[i] = numbers[i] + (long) Math.pow(2, --count);
            }
        }
        
        return answer;
    }
}

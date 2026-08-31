import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int solution(int n) {
        Deque<Integer> stack = new ArrayDeque<>();
        
        while(n != 0){
            stack.push(n%3);
            n /= 3;
        }
        
        int l = stack.size();
        int answer = 0;
        for (int i = 0 ; i < l ; i++){
            answer += (int) Math.pow(3, i) * stack.pop();
        }
    
        return answer;
    }
}
import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> s = new Stack<>();
        int o_length = order.length;
        
        int answer = 0;
        for (int i = 1 ; i <= o_length ; i++){
            if (i == order[answer]) answer++;
            else s.push(i);
            
            while(!s.empty()){
                if (s.peek() == order[answer]){
                    s.pop();
                    answer++;
                } else break;
            }
        }

        return answer;
    }
}
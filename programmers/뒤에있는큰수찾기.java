package programmers;

import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {
        Stack<Integer> s = new Stack<>();
        int l = numbers.length;
        int[] answer = new int[l];

        s.push(0);

        for (int i = 1; i < l ; i++){

            while(!s.empty() && numbers[s.peek()] < numbers[i]){
                answer[s.pop()] = numbers[i];
            }

            s.push(i);
        }

        for (int i = 0 ; i < l ; i++) if (answer[i] == 0) answer[i] = -1;

        return answer;
    }
}
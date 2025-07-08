import java.util.Stack;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> numbers = new Stack<>();

        for (char num : number.toCharArray()){
            while(k > 0 && !numbers.isEmpty() && numbers.peek() < num){
                numbers.pop();
                k--;
            }
            numbers.push(num);
        }

        while(k > 0){
            numbers.pop();
            k--;
        }

        StringBuilder answer = new StringBuilder();
        for (char c : numbers) answer.append(c);

        return answer.toString();
    }
}
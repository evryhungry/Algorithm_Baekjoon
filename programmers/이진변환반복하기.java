class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        while (s.length() > 1) {
            answer[1] += s.length();
            String one = s.replaceAll("0", "");
            s = Integer.toBinaryString(one.length());
            answer[0]++;
            answer[1] -= one.length();
        }
        return answer;
    }
}
// 너무 일찍 일어나버렷다. ㅎ
class Solution {
    public int[] solution(int brown, int yellow) {
        int sum = brown + yellow;
        
        for (int h = 3; h * h <= sum; h++) {
            if (sum % h != 0) continue;

            int w = sum / h;
            if ((w - 2) * (h - 2) == yellow) {
                return new int[]{w, h};
            }
        }
        
        return new int[]{0, 0};
    }
}
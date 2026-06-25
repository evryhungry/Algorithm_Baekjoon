import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int n, long left, long right) {
        List<Integer> answer = new ArrayList<>();
        
        for(long i = left ; i <= right ; i++){
            long r = i / n;
            long c = i % n;
            
            answer.add((int) Math.max(r, c) + 1);
        }

        // return answer.toArray(new int[0]); 아 맞다 기본타입 변경 안되었지..
        return answer.stream()
                     .mapToInt(Integer::intValue)
                     .toArray();
    }
}
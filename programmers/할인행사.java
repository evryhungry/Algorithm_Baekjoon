import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int leavings = 0;
        Map<String, Integer> w_c1 = new HashMap<>();
        
        for (int i = 0 ; i < number.length ; i++) w_c1.put(want[i], number[i]);
        
        int count = 0;
        while(count <= discount.length - 10){
            Map<String, Integer> w_c2 = new HashMap<>();
            
            for (int i = count ; i < (count+10) ; i++){
                w_c2.put(discount[i], w_c2.getOrDefault(discount[i], 0) + 1);
            }
            
            if (w_c1.equals(w_c2)) leavings++; // Map에도 Equals이 있었다는 사실!
            
            count++;
        }
        
        
        return leavings;
    }
}

package programmers;

import java.util.HashMap;
import java.util.Map;

/**
 * 30분
 * 1. topping 의 길이가 100만개라 O(n^2) 은 시간 초과 나올거 같고...
 * 2. 그러면 일단 재귀는 걸러...
 * 3. map으로 for문을 1번만 돌리면 ? O(n) 가능할거 같은데
 * 4. 사이즈만 비교하면 별할거 같다.
 * 5. 실행ㄱㄱ.
 */
class Solution {
    public int solution(int[] topping) {
        int answer = 0;

        Map<Integer, Integer> iron = new HashMap<>();
        for (int t : topping) iron.put(t, iron.getOrDefault(t, 0) + 1);

        Map<Integer, Integer> iron_b = new HashMap<>();
        for (int t : topping){
            iron_b.put(t, iron_b.getOrDefault(t, 0) + 1);
            iron.put(t, iron.get(t) - 1);

            if(iron.get(t) == 0) iron.remove(t);
            if (iron_b.size() == iron.size()) answer++;
        }

        return answer;
    }
}
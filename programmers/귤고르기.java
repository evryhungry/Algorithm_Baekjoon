package programmers;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;

// Map에 넣고 VALUE를 reverse 후(내림차순) 몇 종류만 담으면 되는지 확인.
class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> m = new HashMap<>();
        for(int t : tangerine) m.put(t, m.getOrDefault(t, 0) + 1);

        Integer[] cnt = m.values().toArray(new Integer[0]);
        Arrays.sort(cnt, Collections.reverseOrder());

        int answer = 0;
        for(int c : cnt){
            k -= c ;
            answer++;

            if (k <= 0) break;
        }


        return answer;
    }
}

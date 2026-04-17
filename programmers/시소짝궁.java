package programmers;

import java.util.Map;
import java.util.HashMap;

// 1차 풀이 - 느림 O(n) + 틀림 && 흩허진 메모리.
class Solution {
    public long solution(int[] weights) {
        Map<Integer, Long> m = new HashMap<>();
        long answer = 0;

        for (int i = 0 ; i < weights.length ; i++){
            m.put(weights[i], m.getOrDefault(weights[i], 0L) + 1);
        }

        for (Long value : m.values()) {
            if (2L <= value) answer += (long) value * (value - 1) / 2;
        }

        for (Map.Entry<Integer, Long> entry : m.entrySet()) {
            int key = entry.getKey();
            long value = entry.getValue();

            if (m.containsKey(key * 1 / 2)) answer += value * m.get(key * 1 / 2);
            if (m.containsKey(key * 2 / 3)) answer += value * m.get(key * 2 / 3);
            if (m.containsKey(key * 3 / 4)) answer += value * m.get(key * 3 / 4);
        }

        return answer;
    }
}


// 맞음 시간복잡도 O(n) && 연속된 메모리
class Solution {
    private int[][] ratio = {{1, 2}, {2, 3}, {3, 4}};

    public long solution(int[] weights) {
        int[] count_w = new int[1001];

        for(int weight : weights) count_w[weight]++;

        long answer = 0L;
        for (int i = 100 ; i < 1001; i++){
            if ( count_w[i] == 0 ) continue;

            if ( count_w[i] > 1) answer += (long) (count_w[i] - 1) * count_w[i] / 2;

            for (int[] r : ratio){
                if ( i % r[0] != 0 ) continue;

                int temp = i * r[1] / r[0];

                if(temp > 1000 || count_w[temp] == 0) continue;

                answer += (long) count_w[temp] * count_w[i];
            }
        }

        return answer;
    }
}
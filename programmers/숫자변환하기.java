package programmers;

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public int solution(int x, int y, int n) {
        if(x == y) return 0;

        Queue<int[]> q = new ArrayDeque<>();
        Set<Integer> s = new HashSet<>();

        q.add(new int[]{x, 0});

        while (!q.isEmpty()){
            int[] cur = q.poll();
            int cur_idx = cur[0];
            int cur_value = cur[1];

            for(int i = 0 ; i < 3 ; i++){
                int nxt_idx;
                int nxt_value = cur_value + 1;

                switch (i){
                    case 1 -> { nxt_idx = cur_idx * 2; }
                    case 2 -> { nxt_idx = cur_idx * 3; }
                    default -> { nxt_idx = cur_idx + n; }
                }

                if (s.contains(nxt_idx) || nxt_idx > y) continue;

                if (nxt_idx == y) return nxt_value;
                else {
                    q.add(new int[]{nxt_idx, nxt_value});
                    s.add(nxt_idx);
                }
            }
        }


        return -1;
    }
}

package programmers;

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Collections;


class Solution {
    public int solution(int n, int k, int[] enemy) {
        int eSize = enemy.length;
        Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder()); // 해당 것 사용했어야 함. 틀렸던 이유 왜 이것을 사용해야했을지 생각을 안했던것. (앞에것 부터 사라지는 것이나까 상관 없지 않나? ㄴㄴ -> 이미 담겨져있는 것중 가장 큰거를 깨는게 가장 최선이잖아..)

        if(k>=eSize) return eSize;

        for(int i = 0 ; i < eSize ; i++){
            n -= enemy[i];
            q.add(enemy[i]);
            if (n < 0) {
                if (k > 0) {
                    k--;
                    n += q.poll();
                } else {
                    return i;
                }
            }
        }

        return eSize;
    }
}

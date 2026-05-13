package programmers;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Deque<Integer> states_bridge = new ArrayDeque<Integer>();
        int weigth_on_bridge = 0;

        for (int i = 0 ; i < bridge_length ; i++) states_bridge.add(0); // init


        int idx = 0;
        while(idx < truck_weights.length || !states_bridge.isEmpty()) { // bridge states.

            int truck = states_bridge.pop();
            if (truck != 0) weigth_on_bridge -= truck;

            if(idx < truck_weights.length){
                if (weigth_on_bridge + truck_weights[idx] <= weight) {
                    states_bridge.add(truck_weights[idx]);
                    weigth_on_bridge += truck_weights[idx];
                    idx++;
                } else {
                    states_bridge.add(0);
                }
            }

            answer++;
        }

        return answer;
    }
}
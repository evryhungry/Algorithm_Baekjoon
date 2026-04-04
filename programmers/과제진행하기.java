package programmers;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;


class Solution {

    private int timeToInt (String planTime){
        String[] start_time = planTime.split(":");
        int hh = Integer.parseInt(start_time[0]);
        int mm = Integer.parseInt(start_time[1]);

        return 60 * hh + mm;
    }

    public String[] solution(String[][] plans) {
        ArrayList<String> answer = new ArrayList<>();
        Stack<int[]> stack = new Stack<>();

        Arrays.sort(plans, (a, b) -> timeToInt(a[1]) - timeToInt(b[1]));

        int cur_time = 0;
        int nxt_time = 0;
        int idx_plan = 0;

        while(!stack.isEmpty() || idx_plan < plans.length){

            // 하다가 남은거.
            if (!stack.isEmpty()){

                if (idx_plan == plans.length){
                    answer.add(plans[stack.pop()[0]][0]);
                    continue;
                }

                if (cur_time < nxt_time){

                    int remain_time = stack.peek()[1];
                    int available_time = nxt_time - cur_time;

                    if (remain_time <= available_time){
                        answer.add(plans[stack.pop()[0]][0]);
                        cur_time += remain_time;
                    } else {
                        stack.peek()[1] = remain_time - available_time;
                        cur_time = nxt_time;
                    }


                    continue;
                }
            }

            cur_time = timeToInt(plans[idx_plan][1]) + Integer.parseInt(plans[idx_plan][2]);
            nxt_time = (idx_plan+1 == plans.length) ? 1440 : timeToInt(plans[idx_plan+1][1]);

            if(cur_time > nxt_time){
                stack.push(new int[]{idx_plan, cur_time - nxt_time});
            } else {
                answer.add(plans[idx_plan][0]);
            }

            idx_plan++;
        }

        return answer.toArray(new String[0]);
    }
}
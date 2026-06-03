package programmers;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int last_move = 0;
    long total = 0;
    long q1Sum = 0;
    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    private void init(int[] queue1, int[] queue2){
        int q_length = queue1.length;
        for (int i = 0; i < q_length ; i++){
            total += (queue1[i] + queue2[i]);
            q1Sum += queue1[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }
        last_move = q_length * 3 // 처음에 *2로만 해서 틀림 => 왜 였을까 생각해보니 반례  [1, 1, 1, 1, 1] [1, 1, 1, 9, 1] => 12번 움직여서 가능.;
    }

    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        init(queue1, queue2);
        if (total % 2 == 1) return -1;

        long target = total/2;
        while(true){
            int a = 0;
            if(answer > last_move) return -1;

            if(q1Sum == target) break;
            else if(q1Sum > target){
                a = q1.poll();
                q2.add(a);
                q1Sum -= a;
            } else {
                a = q2.poll();
                q1.add(a);
                q1Sum += a;
            }

            answer++;
        }


        return answer;
    }
}
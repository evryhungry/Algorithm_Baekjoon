package programmers;

import java.util.Queue;
import java.util.PriorityQueue;

class Solution {
    private int INF = 1440;
    private int[] last_time ;
    private Queue<int[]> pq;

    private int timeToString (String book_time){
        String[] time = book_time.split(":");

        int hh = Integer.parseInt(time[0]);
        int mm = Integer.parseInt(time[1]);

        return hh * 60 + mm;
    }
    public int solution(String[][] book_time) {
        int book_size = book_time.length;
        last_time = new int[book_size];
        pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0 ; i < book_size ; i++){
            int start = timeToString(book_time[i][0]);
            int end = timeToString(book_time[i][1]);

            pq.add(new int[]{start, end});
            last_time[i] = INF ;
        }

        int answer = 0;
        while(!pq.isEmpty()){
            int[] book = pq.poll();

            for (int i = 0 ; i < book_size ; i++){
                if (last_time[i] == INF){
                    answer++;
                    last_time[i] = book[1];
                    break;
                }

                if ((last_time[i]  + 10) <= book[0]){
                    last_time[i] = book[1];
                    break;
                }
            }
        }

        return answer;
    }
}

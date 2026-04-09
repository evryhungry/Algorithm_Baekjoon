package programmers;

import java.util.PriorityQueue;
import java.util.Comparator;

class Solution {

    // PriorityQueue로 자동 정렬 및 poll() 순차 처리
    private PriorityQueue<int[]> minerals_count = new PriorityQueue<>(
            Comparator.comparingInt((int[] x) -> x[0])
                    .thenComparingInt(x -> x[1])
                    .thenComparingInt(x -> x[2])
                    .reversed());

    public int solution(int[] picks, String[] minerals) {

        // 전체 캐야하는 갯수와 곡갱이의 사용횟수를 비교하기 위함. (적은것이 더 빨리끝나니까 ㅎㅎ)
        int sum_for_compare = 0;
        for (int i = 0 ; i < picks.length ; i++) {
            sum_for_compare += picks[i] * 5;
        }

        int n = minerals.length;
        if (n > sum_for_compare) n = sum_for_compare;

        int idx = 0;
        int[] tmp = new int[3];
        for (int i = 0 ; i < n ; i++){
            if ((idx % 5) == 0){
                // 초기화의 실수 n + 1 됨
                minerals_count.add(tmp);
                tmp = new int[]{0, 0, 0};
                idx = 0;
            }

            if(minerals[i].equals("diamond")) tmp[0]++;
            else if (minerals[i].equals("iron")) tmp[1]++;
            else tmp[2]++;

            if ( i == (n-1) ) {
                minerals_count.add(tmp);
                break;
            }

            idx++;
        }



        int answer = 0;
        while (!minerals_count.isEmpty()){
            int[] mineral = minerals_count.poll();

            if (picks[0] > 0) {
                answer += mineral[0] + mineral[1] + mineral[2];
                picks[0]--;
            }
            else if (picks[1] > 0) {
                answer += mineral[0] * 5 + mineral[1] + mineral[2];
                picks[1]--;
            }
            else {
                answer += mineral[0] * 25 + mineral[1] * 5 + mineral[2];
                picks[2]--;
            }
        }

        return answer;
    }
}
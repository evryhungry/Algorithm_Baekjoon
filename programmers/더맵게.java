import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) pq.add(s);

        int answer = 0;

        while (!pq.isEmpty() && pq.peek() < K) {
            if (pq.size() < 2) return -1;

            int a = pq.poll();
            int b = pq.poll();
            int mix = a + b * 2;

            pq.add(mix);
            answer++;
        }

        return answer;
    }
}
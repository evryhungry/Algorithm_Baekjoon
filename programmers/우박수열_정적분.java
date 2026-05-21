package programmers;

import java.util.ArrayList;
import java.util.List;

/**
 * 푼시간 25분
 * 접근방법 :
 *  1. 넓이를 구해서 집어 넣고
 *  2. end가 start 보다 작으면 안되니,
 *      누적합을 적용하면 되지 않을까 생각.
 *
 * 아쉬윈점
 * - init을 따로 만들지 못했다는 점
 * - method를 만들어서 풀지 못했다는 것
 */
class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        List<Double> areas = new ArrayList<>();

        while (k > 1) {
            int tmp;
            if (k % 2 == 1) {
                tmp = k * 3 + 1;
            } else {
                tmp = k / 2;
            }

            areas.add((k + tmp) / 2.0);
            k = tmp;
        }


        int n = areas.size();
        double[] prefix = new double[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + areas.get(i);
        }

        for (int i = 0 ; i < ranges.length ; i++){
            int start = ranges[i][0];
            int end = n + ranges[i][1];

            if (start > end) answer[i] = -1.0;
            else answer[i] = prefix[end] - prefix[start];
        }

        return answer;
    }
}
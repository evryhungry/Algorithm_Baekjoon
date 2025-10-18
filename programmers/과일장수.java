/**
 * 과일 장수
 * https://school.programmers.co.kr/learn/courses/30/lessons/135808
 * 문제해석:
 * 1. 과일의 점수가 담긴 배열 score가 주어짐
 * 2. 한 상자에 m개의 과일을 담아 판매
 * 3. 상자는 최대한 많이 만들어야 함
 * 4. 상자에 담긴 과일 중 가장 낮은 점수가 상자의 가격이 됨
 * 5. 과일 장수가 얻을 수 있는 최대 이익을 return
 *
 * 풀이:
 * 1. 점수 배열을 오름차순으로 정렬
 * 2. 뒤에서부터 m개씩 묶어서 상자를 만듦
 * 3. 각 상자의 가격은 m개 중 가장 낮은 점수이므로
 *    정렬된 배열에서 m번째 과일의 점수를 상자의 가격으로 계산
 * 4. 모든 상자의 가격을 더하여 최대 이익을 계산
 *
 * 시간복잡도 O(n log n) (정렬), 공간복잡도 O(1)
 * 5. 푸는데 걸린 시간: 10분
 */

package programmers;

import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        Arrays.sort(score);
        for (int i = score.length; i >= m ; i -= m) {
            answer += score[i - m] * m;
        }
        return answer ;
    }
}

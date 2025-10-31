/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/86491
 * 최소직사각형
 * 푸는데 걸린 시간: 6분
 */
package programmers;

class Solution {
    public int solution(int[][] sizes) {
        int[] x = new int[sizes.length];
        int[] y = new int[sizes.length];
        int maxX = 0;
        int maxY = 0;

        for (int i = 0 ; i < sizes.length ; i++){
            x[i] = Math.max(sizes[i][0], sizes[i][1]);
            y[i] = Math.min(sizes[i][0], sizes[i][1]);
        }

        for (int i = 0 ; i < sizes.length ; i++){
            maxX = Math.max(maxX, x[i]);
            maxY = Math.max(maxY, y[i]);
        }

        return maxX * maxY;
    }
}

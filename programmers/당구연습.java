package programmers;

// 유클리드 거리법 사용
class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0 ; i < balls.length ; i++){
            answer[i] = euclidean(m, n , startX, startY, balls[i][0], balls[i][1]);
        }

        return answer;
    }

    private int euclidean (int m, int n, int startX, int startY, int targetX, int targetY){
        int min_distance = 987654321;

        for (int i = 0 ; i < 4; i++){
            int dx, dy;

            if (i == 0 && startX == targetX && targetY > startY) continue;
            if (i == 1 && startY == targetY && targetX > startX) continue;
            if (i == 2 && startX == targetX && targetY < startY) continue;
            if (i == 3 && startY == targetY && targetX < startX) continue;

            switch (i) {
                case 0 -> { dx = targetX - startX; dy = 2 * n - targetY - startY; }
                case 1 -> { dx = 2 * m - startX - targetX; dy = targetY - startY; }
                case 2 -> { dx = targetX - startX; dy = targetY + startY; }
                default -> { dx = targetX + startX; dy = targetY - startY; }
            }

            min_distance = Math.min(min_distance, dx * dx + dy * dy);
        }

        return min_distance ;
    }
}

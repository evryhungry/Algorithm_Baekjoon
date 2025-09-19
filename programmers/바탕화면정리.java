/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/161990
 * 바탕화면 정리
 * 18분
 * 문제해석:
 * 1. 바탕화면은 50x50 크기의 격자판
 * 2. 각 칸은 '.' 또는 '#'로 채워짐
 * 3. '#'는 바탕화면에 있는 물건을 의미
 * 4. '.'는 빈 칸을 의미
 * 5. 바탕화면에 있는 모든 물건을 포함하는 가장 작은 직
 * 사각형을 찾기
 * 6. 직사각형은 (r1, c1)에서 (r2, c2)까지의 두 점으로 표현
 * 7. (r1, c1)은 직사각형의 왼쪽 위 모서리 좌표
 * 8. (r2, c2)는 직사각형의 오른쪽 아래 모서리 좌표
 */

class Solution {
    public int[] solution(String[] wallpaper) {
        int[] coordinates = {51, 51, 0, 0};

        for (int row = 0 ; row < wallpaper.length ; row++){
            for (int col = 0 ; col < wallpaper[0].length() ; col++){
                char c = wallpaper[row].charAt(col);

                if (c == '#') {
                    coordinates[0] = Math.min(coordinates[0], row);
                    coordinates[1] = Math.min(coordinates[1], col);
                    coordinates[2] = Math.max(coordinates[2], row + 1);
                    coordinates[3] = Math.max(coordinates[3], col + 1);
                }
            }
        }
        return coordinates;
    }
}
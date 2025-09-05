import java.util.*;

/**
 * 30m
 * 완전 탐색(브루트 포스) 기반 구현.
 * https://school.programmers.co.kr/learn/courses/30/lessons/340198
 *
 * <p><b>알고리즘 개요</b><br>
 * - mats를 내림차순 정렬합니다.
 * - park의 각 (row, col) 위치를 순회하며, mats의 각 크기에 대해 해당 위치에 mat x mat 크기의 공간이 모두 "-1"인지 확인합니다.
 * - 만약 가능하다면 해당 크기를 바로 반환합니다.
 * - 모든 위치와 크기를 확인했음에도 불가능하다면 -1을 반환
 *
 * <p><b>시간 복잡도</b><br>
 * - mats 정렬: O(k log k) (k = mats 길이)
 * - 각 위치와 크기 확인: O(R * C * k * m^2) (R = park 행 길이, C = park 열 길이, m = mats 최대값)
 * - 전체: O(k log k + R * C * k * m^2)
 *
 * <p><b>공간 복잡도</b><br>
 * - mats 정렬에 필요한 공간: O(k)
 * - 기타 변수: O(1)
 * - 전체: O(k)
 *
 * <p><b>새로배운것</b><br>
 * - outer 레이블을 사용하여 중첩된 반복문에서 특정 조건이 만족될 때 바깥쪽 반복문으로 바로 탈출하는 방법
 *
 * return answer
 */
class Solution {
    public int solution(int[] mats, String[][] park) {

        Arrays.sort(mats);
        for (int i = 0; i < mats.length / 2; i++) {
            int t = mats[i];
            mats[i] = mats[mats.length - 1 - i];
            mats[mats.length - 1 - i] = t;
        }

        int R = park.length, C = park[0].length;

        for (int mat : mats) {
            if (mat > R || mat > C) continue;
            int maxRow = R - mat;
            int maxCol = C - mat;

            for (int row = 0; row <= maxRow; row++) {
                for (int col = 0; col <= maxCol; col++) {

                    boolean empty = true;
                    outer:
                    for (int r = 0; r < mat; r++) {
                        for (int c = 0; c < mat; c++) {
                            if (!park[row + r][col + c].equals("-1")) {
                                empty = false;
                                break outer;
                            }
                        }
                    }

                    if (empty) return mat;
                }
            }
        }
        return -1;
    }
}
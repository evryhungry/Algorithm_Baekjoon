import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for (int i = 0; i < schedules.length; i++) {
            if (isPossible(schedules[i], timelogs[i], startday)) {
                answer++;
            }
        }

        return answer;
    }

    public boolean isPossible(int standard, int[] day, int startday) {
        int hour = standard / 100;
        int min = standard % 100;
        min += 10;

        if (min >= 60) {
            hour++;
            min -= 60;
        }

        int maxTime = hour * 100 + min;

        for (int i = 0; i < day.length; i++) {
            if (startday == 6 || startday == 7) {
                startday = (startday % 7) + 1;
                continue;
            }

            if (day[i] > maxTime) {
                return false;
            }

            startday = (startday % 7) + 1;
        }

        return true;
    }
}
/**
 * 서버 증설 횟수
 * https://school.programmers.co.kr/learn/courses/30/lessons/120845
 * 문제해석:
 * 1. 하루는 24시간으로 이루어져 있다.
 * 2. 각 시간마다 접속하는 사용자 수가 players 배열에 담겨있다.
 * 3. 한 대의 서버는 최대 m명까지 수용할 수 있다.
 * 4. k시간 동안 서버를 증설할 수 있다.
 * 5. k시간 동안 증설한 서버는 k시간이 지난 후에 사라진다.
 * 6. 모든 사용자를 수용하기 위해 필요한 서버의 최소 대수를 구하라.
 *
 * 문제 해결 방법:
 * 1. 각 시간마다 필요한 서버의 수를 구한다
 * 2. 현제 시간부터 k 시간 전까지의 서버 수를 구한다.
 * 3. 필요한 서버의 수에서 현재 시간부터 k 시간 전까지의 서버 수를 뺀 값을 구한다. = 추가로 필요한 서버 수
 * 4. 추가로 필요한 서버 수가 0보다 크다면, 추가로 필요한 서버 수를 서버 배열에 저장하고, 정답에 추가로 필요한 서버 수를 더한다
 * 5. 모든 시간을 순회한 후 정답을 반환한다.
 *
 * 걸린 시간 : 20분
 */
class Solution {
    public int solution(int[] players, int m, int k) {
        int[] server = new int[24];
        int answer = 0;

        for (int h = 0 ; h < 24 ; h++){
            int needServer = 0;
            if (players[h] >= m){
                needServer += ((players[h] - m) / m) + 1;
            }

            int countServer = 0;
            for (int s = Math.max(0, h - k + 1) ; s <= h ; s++){
                countServer += server[s];
            }

            int add = Math.max(0, needServer - countServer);
            server[h] = add;
            answer += add;
        }
        return answer;
    }
}
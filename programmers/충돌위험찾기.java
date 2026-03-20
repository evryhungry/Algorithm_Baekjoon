package programmers;

import java.util.Queue;
import java.util.LinkedList;

class Solution {
    Queue<int[]>[] time_point;
    int answer;

    private void init(int s){
        time_point = new Queue[s];
        answer = 0;
        for(int i = 0 ; i < s ; i++) time_point[i] = new LinkedList<>();
    }

    public int solution(int[][] points, int[][] routes) {
        int size = routes.length;
        init(size);

        checkPosition(points, routes, size);
        searchCrash(size);

        return answer;
    }


    private void checkPosition(int[][] points, int[][] routes, int s){
        for(int i = 0 ; i < s ; i++){
            int from = routes[i][0] - 1;
            int fromR = points[from][0]; int fromS = points[from][1];
            time_point[i].add(new int[]{fromR, fromS});

            for(int j = 1 ; j < routes[i].length; j++){ // 이 부분에서 틀렸음.
                int to = routes[i][j] - 1;
                int toR = points[to][0]; int toS = points[to][1];

                while (fromR != toR){
                    if (fromR < toR) fromR++;
                    else fromR--;
                    time_point[i].add(new int[]{fromR, fromS});
                }

                while (fromS != toS){
                    if (fromS < toS) fromS++;
                    else fromS--;
                    time_point[i].add(new int[]{fromR, fromS});
                }
            }
        }
    }

    private void searchCrash(int s){
        int cfr = 0;

        while(cfr != s){
            int[][] map = new int[101][101];
            cfr = 0;

            for (int i = 0 ; i < s ; i++){
                if (time_point[i].isEmpty()) {
                    cfr++;
                    continue;
                }

                int[] tmp = time_point[i].poll();
                map[tmp[0]][tmp[1]]++;
            }


            for (int r = 1; r < 101; r++){
                for (int c = 1 ; c < 101; c++){
                    if (map[r][c] > 1) answer++;                }
            }
        }
    }
}

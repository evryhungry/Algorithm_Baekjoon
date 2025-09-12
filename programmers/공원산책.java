import java.util.Map;

/**
 * 첫 풀이 와 별로인데 (효율성 테스트 0점) 다시 풀기
 * 왜 별로인가
 * 1. 방향을 숫자로 매핑한건 좋은데, 동서남북이 아니라 동서북남으로 해서 헷갈림
 * 2. x,y 좌표를 반대로 생각함
 * 3. 장애물 체크하는 로직이 너무 복잡함
 * 4. temp 변수를 쓸 필요가 없음
 * 5. isOb 변수도 불필요
 * 6. 변수명도 직관적이지 않음
 * 8. 전체적으로 코드가 길고 복잡함
 * 12. 2중 for문을 사용하여 park 배열을 순회하는 것도 성능에 영향을 줄 수 있음
 * 13. 불필요한 변수를 많이 사용하는 것도 성능에 영향을 줄 수 있음
 * 14. 코드의 가독성이 떨어짐
 * 15. 유지보수가 어려움
 * 즉, 코드의 효율성과 가독성을 높이기 위해서는 불필요한 변수를 줄이고, 직관적인 변수명을 사용하며, 복잡한 로직을 단순화하는 것이 중요함
 * 그래서 제출을 후 다시 풀어본다.
 */
class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        int[] d = [1, -1, -1, 1];
        int x_length = park[0].length;
        int y_length = park.length
        Map<String, Integer> map = Map.of(
                "E", 0,
                "W", 1,
                "S", 2,
                "N", 3
        );
        int[][] park_graph = new int[y_length][x_length];
        for (int i = 0 ; i < y_length ; i++) {
            for (int j = 0 ; j < x_length ; j++) {
                switch (park[i].charAt(j)) {
                    case 'S':
                        park_graph[j][i] = 0;
                        answer[0] = j;
                        answer[1] = i;
                        break;
                    case 'O':
                        park_graph[j][i] = 0;
                        break;
                    default:
                        park_graph[j][i] = -1;
                }
            }
        }

        for (String route : routes){
            String[] part = route.split(" ");
            String direction = part[0];
            int distence = Integer.parseInt(part[1]);
            boolean isOb = false;
            int temp = 0

            if (direction.equals("E") || direction.equals("W")){
                temp += answer[0] + d[map.get(direction)] * distence ;

                if (temp >= 0 && temp < x_length) {
                    int x = 0
                    for (int i = 0 ; i < distence ; i++){
                        x += d[map.get(direction)]
                        if(park_graph[answer[1]][answer[0] + x] == -1) {
                            isOb = true;
                            break
                        }
                    }

                }
                if (isOb) continue;
                answer[0] = temp;
            } else {
                temp += answer[1] + d[map.get(direction)] * distence ;
                if (temp >= 0 && temp < y_length) {
                    int y = 0
                    for (int i = 0 ; i < distence ; i++){
                        y += d[map.get(direction)]
                        if(park_graph[answer[1] + y][answer[0]] == -1) {
                            isOb = true;
                            break
                        }
                    }
                }
                if (isOb) continue;
                answer[1] = temp;
            }
        }
        return answer;
    }
}

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/172928
 * 공원 산책
 * 두번째 풀이
 * 1. 방향을 동서남북 순으로 바꿈
 * 2. x,y 좌표를 직관적으로 바꿈
 * 3. 장애물 체크하는 로직을 단순화함
 * 4. temp 변수를 제거함
 * 5. isOb 변수를 제거함
 * 6. 변수명을 직관적으로 바꿈
 * 7. 객체 지향적으로 바꿈 내부 함수로 역할 분리 아 보기 편하다.. 
 * 즉 전체적으로 코드가 짧고 간결해짐
 * 효율성 테스트도 통과함
 *
 */

import java.util.Map;
class Solution {
    private final Map<String, int[]> DIR = Map.of(
            "E", new int[]{0, 1},
            "W", new int[]{0, -1},
            "S", new int[]{1, 0},
            "N", new int[]{-1, 0}
    );

    private static boolean isBound(int x, int y, int maxX, int maxY) {
        return x >= 0 && x < maxX && y >= 0 && y < maxY;
    }

    private static boolean isValidMove(int startX, int startY, int[][] graph, String direction, int distance) {
        int[] move = DIR.get(direction);
        for (int step = 1; step <= distance; step++) {
            int newX = startX + move[0] * step;
            int newY = startY + move[1] * step;
            if (!isBound(newX, newY, graph.length, graph[0].length) || graph[newX][newY] == -1) {
                return false;
            }
        }
        return true;
    }



    public int[] solution(String[] park, String[] routes){
        int startX = 0, startY = 0;
        int rows = park.length;
        int cols = park[0].length();
        int[][] graph = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char cell = park[i].charAt(j);
                if (cell == 'S') {
                    startX = i;
                    startY = j;
                    graph[i][j] = 0;
                } else if (cell == 'O') {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = -1;
                }
            }
        }

        for (String route : routes) {
            String[] parts = route.split(" ");
            String direction = parts[0];
            int distance = Integer.parseInt(parts[1]);

            if (isValidMove(startX, startY, graph, direction, distance)) {
                int[] move = DIR.get(direction);
                startX += move[0] * distance;
                startY += move[1] * distance;
            }
        }

        return new int[]{startX, startY};
    }
}
import java.util.Set;
import java.util.HashSet;

// https://school.programmers.co.kr/learn/courses/30/lessons/49994
/**
 * 프로그래머스 - 방문 길이 
 * 걸린 시간 : 30min
 * 
 * Idea: Set
 * 1. 좌표를 기준으로 이동한 경로를 문자열로 만들어 Set에 넣어 중복을 제거한다.
 * 2. 이동한 경로가 이미 존재하면 continue, 존재하지 않으면 Set에 추가하고 cnt를 증가시킨다.
 * 
 */

class Solution {
    public int solution(String dirs) {
        int cx = 0, cy = 0;
        Set<String> path = new HashSet<>();
        
        int cnt = 0;
        for (char dir : dirs.toCharArray()){
            int nx = cx, ny = cy;
            
            switch(dir){
                case 'U':
                    ny++; break;
                case 'D':
                    ny--; break;
                case 'R':
                    nx++; break;
                case 'L':
                    nx--; break;  
            }
            
            if (ny > 5 || nx > 5 || ny < -5 || nx < -5) continue;
            
            String move1 = cx+""+cy+nx+ny;
            String move2 = nx+""+ny+cx+cy;
            cx = nx;
            cy = ny;
            
            if (path.contains(move1) || path.contains(move2)) continue;
            path.add(move1);
            path.add(move2);
            cnt++;
        }
        
        
        return cnt;
    }
}
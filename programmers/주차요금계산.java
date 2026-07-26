import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * 주차 요금 계산 (https://school.programmers.co.kr/learn/courses/30/lessons/92341, 걸린시간: 1번째 풀이 20분, 2번째 풀이 10분)
 * 1. 초기 풀이 -> 실패
 *  - 주차 시간 계산시 총 주차 시간 계산이 잘못됨(차량이 여러번 입출차한 경우) => 총 주차시간이 아닌 마지막 입차시간과 마지막 출차시간의 차이로 계산됨
 * 
 * 2. 개선 풀이
 * - 해당 차량의 총 주차되는 시간을 Map에 저장
 */
class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTimeMap = new HashMap<>();
        Map<String, Integer> totalTimeMap = new TreeMap<>();
        
        for (String record : records) {
            String[] r = record.split(" ");
            int time = changeMin(r[0]);
            String car = r[1];
            
            if (r[2].equals("IN")) {
                inTimeMap.put(car, time);
            } else {
                int inTime = inTimeMap.get(car);
                int parkedTime = time - inTime;
                
                totalTimeMap.put(car, totalTimeMap.getOrDefault(car, 0) + parkedTime);
                inTimeMap.remove(car);
            }
        }
        
        int MAX = 1439;
        for (String car : inTimeMap.keySet()) {
            int inTime = inTimeMap.get(car);
            int parkedTime = MAX - inTime;
            totalTimeMap.put(car, totalTimeMap.getOrDefault(car, 0) + parkedTime);
        }
        
        int[] answer = new int[totalTimeMap.size()];
        int index = 0;
        
        for (int totalTime : totalTimeMap.values()) {
            answer[index++] = calWon(fees, totalTime);
        }
        
        return answer;
    }
    
    private int changeMin(String times) {
        String[] t = times.split(":"); 
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
    
    private int calWon(int[] fees, int totalTime) {
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        if (totalTime <= baseTime) {
            return baseFee;
        }
        
        int extraTime = totalTime - baseTime;
        return baseFee + (int) Math.ceil((double) extraTime / unitTime) * unitFee;
    }
}
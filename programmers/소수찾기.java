import java.util.Set;
import java.util.HashSet;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42839
 * Solution
 * 
 * 백트래킹 문제.
 * 
 * 걸린시간 : 23분
 * 
 * 백트레킹을 머리로 구현하고 하나씩 집혀가면서 구현했다. -> 글로 작성하고 구현하자, 중간중간 애먹더라.
 * 
 * 아쉬운점 : 짝수를 먼저 전부 배제했으면 얼마나 좋았을까? 2를 제외한 모든 짝수는 소수가 아니므로, 후보군을 만들때 짝수를 배제했으면 더 빨리 끝났을듯.
 */
class Solution {
    public int solution(String numbers) {
        Set<Integer> candidates = new HashSet<>();
        boolean[] visited = new boolean[numbers.length()];
        StringBuilder sb = new StringBuilder();
        makeCandidates(numbers, sb, visited, candidates);
      
        int answer = 0;
        for (int num : candidates) {
            if (checkDecimal(num)) {
                answer++;
            }
        }
        return answer;
    }
    
    private void makeCandidates(String numbers, StringBuilder sb, boolean[] visited, Set<Integer> candidates) {
        if (sb.length() > 0) {
            candidates.add(Integer.parseInt(sb.toString()));
        }
        
        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                sb.append(numbers.charAt(i));
                makeCandidates(numbers, sb, visited, candidates);
                sb.deleteCharAt(sb.length() - 1);
                visited[i] = false;
            }
        }
    }
    
    private boolean checkDecimal(int number){
        if (number < 2) return false;
        else if( number == 2 || number == 3 ) return true;

        int sq = (int) Math.sqrt(number);        
        for (int i = 2 ; i <= sq ; i++){
            if ( number % i == 0 ) return false;
        }
        
        return true;
    }
}
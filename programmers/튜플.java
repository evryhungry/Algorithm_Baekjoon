/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/64065?language=java
 * 
 * 문제 설명
 *  
 *  튜플은 다음과 같이 정의할 수 있습니다.
 *  - 튜플은 순서가 있는 집합입니다.
 *  - 튜플은 중복된 원소를 가질 수 있습니다.
 *  - 예를 들어, (2, 1, 2, 3)은 길이가 4인 튜플입니다. 
 *  - (1, 2, 3)과 (3, 2, 1)은 같은 튜플입니다.
 * 
 * 풀이
 * 1. 문자열을 파싱하여 각 집합을 추출합니다.
 * 2. 각 집합을 원소의 개수에 따라 정렬합니다.
 * 3. 정렬된 집합을 순회하면서, 이전에 등장하지 않은 원소를 결과 리스트에 추가합니다.
 * 
 * 풀고나서 아쉬운점
 * 1. 문자열 파싱 과정에서 정규식을 사용하여 더 간결하게 처리할 수 있었을 것 같다. // s.substring(2, s.length() - 2).split("\\},\\{") 한툴처리 가능한데.. 아숩 
 * 2. int[] answer = new int[answer.size()]; 부분으로 처리 후, groups를 index로 접근하여 처리해도 괜찮지 않았을까?
 *    // for (int i = 0; i < answer.size(); i++) { result[i] = answer.get(i); } -> for (int i = 0; i < groups.length; i++) { answer[i] = Integer.parseInt(groups[i]); } 
 * 3. Comparator 의 사용을 더 기억해야할 것 같다.
 * 4. 람다식 사용을 더 기억해야할 것 같다. // 적용하는데 조금 시간이 걸린다는 점이 아쉽습니다.
 * 
 * (식)구상 시간 / 풀이 시간 / 총 시간
 * 5min / 40min / 45min
 */

import java.util.*;

class Solution {
    public int[] solution(String s) {
    
        //
        s = s.substring(2, s.length() - 2);
        String[] groups = s.split("\\},\\{");
        
        Arrays.sort(groups, Comparator.comparingInt(group -> group.split(",").length));
        //
        
        List<Integer> answer = new ArrayList<>();
        Set<Integer> used = new HashSet<>();
        
        for (String group : groups) { 
            String[] numbers = group.split(",");
            
            for (String number : numbers) {
                int num = Integer.parseInt(number);
            
                if (!used.contains(num)) {
                    answer.add(num);
                    used.add(num);
                }
            }
        }
        
        int[] result = new int[answer.size()];
        
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }
        
        return result;
    }
}
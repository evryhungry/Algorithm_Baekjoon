// https://school.programmers.co.kr/learn/courses/30/lessons/43165

/**
 * 
 * Solution
 * 푼 시간 : 10m
 * 
 * dfs로 힌트를 줘서 빠르게 푼것 같다.
 * 
 * 풀이
 * 1. dfs를 이용하여 numbers의 각 원소를 더하거나 빼는 경우를 모두 탐색한다.
 * 2. numbers의 길이와 index가 같아지면 sum과 target을 
 *    비교하여 같으면 1, 다르면 0을 반환한다.
 * 
 * 시간복잡도 : O(2^n)
 * 
 * 접근법 : 하나씩 더하거나 빼는 경우가 있으므로, dfs로 모든 경우를 탐색하면 되지 앟을까 생각.
 *  최대 갯수가 20개이므로, 2^20 = 1,048,576 이므로 충분히 가능할 것 같다.
 *  바로 시작.
 */
class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }
    
    private int dfs(int[] numbers, int target, int sum, int index){
        if (numbers.length == index){
            return sum == target ? 1 : 0;
        }
        
        return dfs(numbers, target, sum + numbers[index], index + 1) +
               dfs(numbers, target, sum - numbers[index], index + 1);
    }
}
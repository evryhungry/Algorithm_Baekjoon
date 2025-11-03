/**
 * 부족한 금액 계산하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/82612
 * 문제해석:
 * 1. 놀이기구의 이용료는 price원
 * 2. 놀이기구는 count번째 이용할 때 이용료가 price x count원
 * 3. 처음 가진 금액은 money원
 * 4. 놀이기구를 count번 타기 위해서 필요한 금액이 얼마인지 계산
 * 5. 만약 처음 가진 금액이 부족하지 않다면 0을 반환
 *
 * 문제 해결방법:
 * 1. 1부터 count까지의 합을 구함
 * 2. 합에 price를 곱함
 * 3. 합에서 money를 뺌
 * 4. 결과가 음수라면 0을 반환, 양수라면 그 값을 반환
 *
 * 시간복잡도:
 * O(n)
 *
 * 푸는데 걸린 시간 : 1분
 * 고찰: 등차수열의 합 공식을 사용하면 더 빠르게 풀 수 있을 것 같다.
 */
package programmers;

class Solution {
    public long solution(int price, int money, int count) {
        long sum = 0L;
        for(int i = count ; i > 0 ; i--) sum += (long)i * (long)price;
        return sum - (long)money > 0 ? sum - (long)money : 0;
    }
}
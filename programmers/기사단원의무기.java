/**
 * 기사단원의 무기
 * https://school.programmers.co.kr/learn/courses/30/lessons/136798
 *
 * 문제해석:
 * 1. 기사단원의 무기 개수를 구하는 문제
 * 2. 각 기사단원은 1부터 number까지 번호가 매겨져 있고, 각 번호에 해당하는 무기 개수는 해당 번호의 약수 개수와 같다.
 * 3. 단, 약수 개수가 limit를 초과하는 경우에는 power로 대체한다.
 * 4. 모든 기사단원의 무기 개수를 합산하여 return 한다.
 *
 * 풀이:
 * 1. 1부터 number까지 반복문을 돌면서 각 번호 i에 대해 약수 개수를 구한다.
 * 2. 약수 개수를 구할 때는 j를 1부터 √i까지 반복하면서 i가 j로 나누어 떨어지는지 확인한다.
 * 3. 나누어 떨어지면 약수 개수를 2 증가시키고, j*j가 i와 같으면 약수 개수를 1 증가시킨다.
 * 4. 약수 개수가 limit를 초과하면 power를 더하고, 그렇지 않으면 약수 개수를 더한다.
 * 5. 최종적으로 모든 기사단원의 무기 개수를 합산한 값을 return 한다.
 *
 * 시간 복잡도 O(n√n), 공간복잡도 O(1)
 * 푸는데 걸린 시간 : 10분
 */

package programmers;

public class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;

        for(int i = 1 ; i <= number ; i++){
            int count = 0;
            for (int j = 1 ; j * j <= i ; j++) {
                if (j * j == i){
                    count++;
                } else if (i % j == 0){
                    count += 2;
                }
            }

            answer += (count > limit) ? power : count;
        }
        return answer;
    }
}{
}

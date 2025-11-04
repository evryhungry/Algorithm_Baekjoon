/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/77484
 * 로또 최고순위와 최저순위
 * 문제를 풀은 시간 : 10분
 * 문제해석:
 * 1. 로또 번호는 1부터 45까지의 숫자 중 6개를 고름
 * 2. 로또 당첨 번호 6개와 구매한 로또 번호 6개가 주어짐
 * 3. 구매한 로또 번호에는 0이 포함될 수 있음
 *   - 0은 어떤 숫자로도 바꿀 수 있는 번호
 * 4. 당첨 가능한 최고 순위와 최저 순위를 알아내는 문제
 * 풀이:
 * 1. HashMap을 사용하여 구매한 로또 번호를 저장
 * 2. 0의 개수를 세기
 * 3. 당첨 번호와 구매한 로또 번호를 비교하여 일치하는 개수를 세기
 * 4. 최고 순위는 일치하는 개수 + 0의 개수
 * 5. 최저 순위는 일치하는 개수
 * 6. 순위는 7 - 일치하는 개수로 계산
 *   - 단, 일치하는 개수가 0인 경우는 6등으로 처리
 *
 * 예상 시간복잡도: O(1)
 */

package programmers;

import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i < 6 ; i++) map.put(lottos[i], map.getOrDefault(lottos[i], 0) + 1);

        if (map.containsKey(0) && map.get(0) == 6) return new int[]{1, 6};

        int contain = 0;
        for (int i = 0 ; i < 6 ; i++) if(map.containsKey(win_nums[i])) contain++;

        if (contain == 0) return new int[]{6, 6};
        return new int[]{7 - (contain + map.getOrDefault(0, 0)), 7 - contain };
    }
}

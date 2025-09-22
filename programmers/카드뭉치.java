/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/159994
 * 카드 뭉치
 * 5분
 * 문제 설명:
 * 1. 카드 뭉치가 두 개 있습니다.
 * 2. 각 카드 뭉치는 문자열 배열로 주어집니다.
 * 3. 카드 뭉치에서 카드를 하나씩 순서대로
 *   뽑아 원하는 목표 문자열 배열을 만들려고 합니다.
 * 4. 카드 뭉치에서 카드를 뽑을 때는
 *  각 카드 뭉치에서 순서대로만 뽑을 수 있고,
 *  한 번 뽑은 카드는 다시 넣을 수 없습니다.
 * 5. 원하는 목표 문자열 배열을 만들 수 있다면 "Yes"를 아니면 "No"를 return 하는 solution 함수를 완성해주세요.
 *
 * 풀이 :
 * 1. goal 배열을 순회하면서 cards1, cards2의 현재 위치를 가리키는 인덱스 변수를 각각 하나씩 증가시킨다.
 * 2. 만약 goal 배열의 현재 값이 cards1, cards2의 현재 값과 모두 다르다면 "No"를 return 한다.
 * 3. goal 배열을 모두 순회했다면 "Yes"를 return 한다.
 * 4. 인덱스를 넘어가지 않게 하면 끝.
 * 5. 시간복잡도 O(n), 공간복잡도 O(1)
 */
class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int cards1_count = 0;
        int cards2_count = 0;

        for (String card : goal){
            if (cards1_count < cards1.length && cards1[cards1_count].equals(card)) cards1_count++;
            else if (cards2_count < cards2.length && cards2[cards2_count].equals(card)) cards2_count++;
            else return "No";
        }
        return "Yes";
    }
}
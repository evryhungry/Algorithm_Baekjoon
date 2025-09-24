/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/150370
 * 개인정보 수집 유효기간
 * 2023 KAKAO BLIND RECRUITMENT
 * 50분
 * 문제 설명:
 * 1. 오늘 날짜 today, 약관 종류와 유효기간 terms,
 *   수집된 개인정보의 수집일자와 약관 종류 privacies가 매
 *   개변수로 주어집니다.
 * 2. 각 개인정보의 수집일자로부터 유효기간이 지난
 *   개인정보의 번호를 오름차순으로 return 하도록 solution
 *   함수를 완성해주세요.
 *  풀이 :
 *  1. today를 일수로 변환
 *  2. terms를 맵으로 변환
 *  3. privacies를 순회하면서 수집일자를 일수로 변환
 *  4. 수집일자 + 유효기간 * 28 - 1 < today_days면 expired에 추가
 *  5. expired를 오름차순으로 정렬 후 반환
 *  6. 시간복잡도 O(n), 공간복잡도 O(n)
 */

import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> term_map = new HashMap<>();
        List<Integer> expired = new ArrayList<>();
        int today_days = toDays(split_day(today));

        for (String term : terms){
            String[] dis_term = term.split(" ");
            term_map.put(dis_term[0], Integer.parseInt(dis_term[1]));
        }

        for (int i = 0; i < privacies.length ; i++){
            String[] privacy = privacies[i].split(" ");
            int[] daily = split_day(privacy[0]);

            int valid_until = toDays(daily) + term_map.get(privacy[1]) * 28 - 1;

            if (today_days > valid_until) {
                expired.add(i + 1);
            }
        }


        return expired.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[] split_day (String day){
        String[] string_day = day.split("\\.");
        return new int[]{Integer.parseInt(string_day[0]),
                Integer.parseInt(string_day[1]),
                Integer.parseInt(string_day[2])};
    }

    private static int toDays(int[] ymd) {
        int y = ymd[0], m = ymd[1], d = ymd[2];
        return y * 12 * 28 + (m - 1) * 28 + d;
    }
}
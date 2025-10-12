/**
 * 이모티콘 할인행사
 * https://school.programmers.co.kr/learn/courses/30/lessons/150368
 * 출처 : 2024 카카오 신입 공채 1차 코딩테스트
 * 다른 사람의 풀이를 보고 작성: https://trillium.tistory.com/163 (완전탐색 + 시뮬레이션)
 * 1. 가능한 모든 할인율 조합을 완전탐색으로 구한다.
 * 2. 각 조합에 대해 모든 사용자의 구매 행동을 시뮬레이션한다.
 * 3. 각 조합에서 얻은 결과를 바탕으로 최대 이모티콘 플러스 가입자 수와 매출액을 비교하여 최적의 결과를 갱신한다.
 * 4. 최종적으로 최대 가입자 수와 매출액을 반환한다.
 * 시간 복잡도: O(4^m * n) (m은 이모티콘의 수, n은 사용자의 수)
 * 공간 복잡도: O(m) (재귀 호출 스택과 이모티콘 리스트 저장을 위한 공간
 */

import java.util.*;

class Solution {
    static int[] discountRate = {10, 20, 30, 40};
    static int[] answer = new int[2];
    static List<Emoticon> emoList = new ArrayList<>();

    public int[] solution(int[][] users, int[] emoticons) {
        recurEmoticon(users, emoticons, 0);
        return answer;
    }

    static void recurEmoticon(int[][] users, int[] emoticons, int index){
        if(index == emoticons.length){
            int[] result = proceedUser(users);
            // 우선순위 1.
            if (answer[0] < result[0]){
                answer = result.clone();
            }
            // 우선순위 2.
            if (answer[0] == result[0] && answer[1] < result[1]){
                answer = result.clone();
            }
            return;
        }

        for (int i = 0; i < 4; i++){
            int rate = discountRate[i];
            int discountPrice = emoticons[index] * (100 - rate) / 100;

            // 다른 rate의 emoticon 가격을 사용하는 경우를 쓰기위함. add, remove
            emoList.add(new Emoticon(rate, discountPrice));
            recurEmoticon(users, emoticons, index + 1);
            emoList.remove(emoList.size() - 1);
        }
    }

    static int[] proceedUser(int[][] users){
        int plusMember = 0;
        int totalPrice = 0;

        for (int i = 0; i < users.length ; i++){
            int userWishRate = users[i][0];
            int userMaxPrice = users[i][1];

            int userPurchasePrice = 0;
            for (Emoticon emo : emoList){
                if(userWishRate <= emo.rate){
                    userPurchasePrice += emo.price;
                }
            }

            // 허용가격 이상 구매하는지 판단.
            if (userPurchasePrice >= userMaxPrice){
                plusMember ++;
            } else {
                totalPrice += userPurchasePrice;
            }

        }

        return new int[]{plusMember, totalPrice};
    }

    static class Emoticon {
        int rate; // 이모티콘 할인율.
        int price; // 할인율을 적용한 가격.

        Emoticon(int rate, int price){
            this.rate = rate;
            this.price = price;
        }
    }
}
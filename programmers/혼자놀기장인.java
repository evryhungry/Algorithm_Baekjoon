package programmers;

class Solution {
    int[] card_list ;

    public int solution(int[] cards) {
        card_list = cards;
        int c_length = cards.length;

        int answer = 0;

        int f = 0 ;
        int s = 0 ;

        for (int i = 0 ; i < c_length ; i++){
            if (card_list[i] == 0) continue;

            int check = checkCount(card_list[i] - 1);

            if (check > s){
                if (check > f) {
                    s = f ;
                    f = check;
                    continue;
                }

                s = check;
            }
        }

        return f * s;
    }

    private int checkCount(int card){
        if (card_list[card] == 0) return 0;

        int i = card_list[card] - 1;

        card_list[card] = 0;

        return checkCount(i) + 1;
    }
}

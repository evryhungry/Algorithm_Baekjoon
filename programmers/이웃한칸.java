class Solution {
    public int solution(String[][] board, int h, int w) {
        String color = board[h][w];
        int length = board.length;
        int count = 0;
        int[] dh = {0, 1, -1, 0};
        int[] dw = {1, 0, 0, -1};

        for (int i = 0 ; i < 4; i++){
            int hc = h + dh[i];
            int wc = w + dw[i];
            if (hc < 0 || hc >= length || wc < 0 || wc >= length) continue;
            if (color.equals(board[hc][wc])) count++;
        }

        return count;
    }
}
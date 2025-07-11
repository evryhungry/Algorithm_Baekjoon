import java.util.Stack;

class Solution {
    static int n ;

    public int solution(int[][] board, int[] moves) {
        n = board.length;

        int boom = 0;
        Stack<Integer> stack = new Stack<>();

        for (int move : moves){
            int move_index = move - 1;
            int pick = pickTop(board, move_index);

            if (pick != 0){
                if (!stack.isEmpty() && pick == stack.peek()){
                    boom += 2;
                    stack.pop();
                } else {
                    stack.push(pick);
                }
            }
        }

        return boom;
    }

    public static int pickTop(int[][] board, int col) {
        for (int i = 0; i < n; i++) {
            if (board[i][col] != 0) {
                int doll = board[i][col];
                board[i][col] = 0;
                return doll;
            }
        }
        return 0;
    }
}
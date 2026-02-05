import java.io.*;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2239

/**
 * 실패사유 1. endRow와 endCol의 인덱신 초과.
 * 실패사유 2. input의 not trim 화
 *
 * 문제정의. 스토쿠
 * 1. 같은 열, 같은 행, 같은 3 * 3 내에 본인과 같은 숫자가 없어야함.
 * 2. 같은 숫자가 있다면 backtracking으로 다른 숫자 넣어보기
 * 3. 다른 숫자라면 바로 다음으로 열과행으로 넘어가기.
 */
public class Main{
    static int[][] puzzle = new int[9][9];

    private static boolean isPossible(int row, int col, int pos){

        for (int i = 0; i < 9; i++){
            if(i == col) continue;
            if(puzzle[row][i] == pos) return false; // row 검사.
        }

        for (int i = 0; i < 9; i++){
            if(i == row) continue;
            if (puzzle[i][col] == pos) return false; // col 검사.
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        int endRow = startRow + 3;
        int endCol = startCol + 3;

        for (int i = startRow; i < endRow; i++){
            for (int j = startCol; j < endCol; j++){
                if (puzzle[i][j] == pos) return false; // 3 * 3 검사.
            }
        }

        return true;
    }

    private static boolean solve(int row, int col){

        if (col == 9){
            return solve(row + 1,0);
        } else if (row == 9){
            return true; // 끝까지 간것.
        }

        if (puzzle[row][col] == 0){
            for (int k = 1; k <= 9; k++){
                if (isPossible(row, col, k)){ // 유효성 검사.
                    puzzle[row][col] = k;
                    boolean isSolved = solve(row, col + 1);
                    if (isSolved) return true; // 되는 것.
                    puzzle[row][col] = 0;
                }
            }
            return false;
        }

        else {
            return solve(row, col + 1);
        }
    }



    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 9; i++) {
            String line = br.readLine().trim(); // 안 나누어져 있다는 것을 잊어버림.
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = line.charAt(j) - '0'; // ASCII code
            }
        }

        solve(0, 0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(Integer.toString(puzzle[i][j])); // Integer로 정의 안하면 memory에 대한 정보 들어감
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
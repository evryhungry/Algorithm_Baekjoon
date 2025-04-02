import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/10830
public class Main {
    static int N;
    static long B;
    static int[][] matrix;
    static int mod = 1000;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();


        int[][] result = square(matrix, B);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int[][] multiply(int[][] tmp1, int[][] tmp2) {
        int[][] ret = new int[N][N];

        for (int row = 0 ; row < N ; row++) {
            for (int col = 0 ; col < N ; col++) {
                int sum = 0;
                for (int i = 0; i < N; i++) {
                    sum += tmp1[row][i] * tmp2[i][col];
                }
                ret[row][col] = sum % mod;
            }
        }

        return ret;
    }

    static int[][] square(int[][] a, long b) {
        if (b == 1){
            int[][] res = new int[N][N];
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    res[i][j] = a[i][j] % mod;
                }
            }
            return res;
        }

        int[][] temp = square(a, b / 2);
        if (b % 2 == 0){
            temp = multiply(temp, temp);
        } else {
            temp = multiply(multiply(temp, temp), a);
        }

        return temp;

    }
}
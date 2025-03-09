import java.io.*;
import java.util.*;

// Tstory 참고.
// https://www.acmicpc.net/problem/11444
public class Main {
    static long mod = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        if (n == 0 || n == 1) {
            System.out.println(n);
            return;
        }


        long[][] matrix = {{1, 1} , {1, 0}};
        long[][] a = {{1, 0} , {0, 1}}; // 초기값

        n--;
        while (n > 0){

            if(n % 2 == 1) {
                a = multiply(a, matrix);
            }
            matrix = multiply(matrix, matrix);

            n /= 2;
        }

        System.out.println(a[0][0]);
    }

    // 행렬울 곱하주기
    public static long[][] multiply(long[][] a, long[][] b) {
        long[][] ret = new long[2][2];

        ret[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % mod;
        ret[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % mod;
        ret[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % mod;
        ret[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % mod;

        return ret;
    }
}

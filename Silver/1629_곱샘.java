import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/1629

/*
 * 곱샘,
 * 분할을 이용해서 푸는 방식.
 */

public class Main {
    static int d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        long result = power(C, n);

        System.out.println(result);
    }

    static long power(int C, int n){

        if(n == 1) {
            return C % d;
        }

        long temp = power(C, n / 2);

        if(n % 2 == 1) {
            return (temp * temp % d) * C % d;
        }
        return temp * temp % d;
    }
}
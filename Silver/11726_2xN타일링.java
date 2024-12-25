import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 11726_2xn타일링
 * 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
 * 아래 그림은 2×5 크기의 직사각형을 채운 한 가지 방법의 예이다.
 */
public class Main {
    static int[] cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        cnt = new int[n+1];
        cnt[0] = 1;
        cnt[1] = 1;
        System.out.println(fibonacci(n));
    }

    public static int fibonacci(int n) {
        if (cnt[n] != 0)
            return cnt[n];
        return cnt[n] = (fibonacci(n-1) + fibonacci(n-2)) % 10007;
    }
}

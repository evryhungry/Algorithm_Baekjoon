import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11727
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
        return cnt[n] = (fibonacci(n-1) + 2 * fibonacci(n-2)) % 10007;
    }
}

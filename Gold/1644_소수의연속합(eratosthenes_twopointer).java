import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1644
public class Main {
    static int N, cnt;
    static List<Integer> decimals;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        cnt = 0;
        decimals = new ArrayList<>();
        checked = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            if (i == 1 || i == 0){
                checked[i] = false;
                continue;
            }
            checked[i] = true;
        }

        getPrimeEratosthenes();
        twoPointer();

        bw.write(cnt + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void getPrimeEratosthenes(){
        if (N == 1 || N == 0) return;

        int n = (int) Math.sqrt(N) + 1;
        int j = 0;
        for (int i = 2; i <= n; i++) {
            if (checked[i]){
                j = 2;
                while(i * j <= N){
                    checked[i * j] = false;
                    j += 1;
                }
            }
        }
        for (int i = 2; i <= N; i++) {
            if (checked[i]) {
                decimals.add(i);
            }
        }
    }

    static void twoPointer(){
        if(decimals.isEmpty()) return;

        int left = 0;
        int right = 0;
        int sum = 0;
        int size = decimals.size();
        while (left <= right){
            if (sum >= N){
                sum -= decimals.get(left++);
            } else if (right == size) break;
            else {
                sum += decimals.get(right++);
            }

            if (sum == N) cnt++;
        }

        return;
    }
}s
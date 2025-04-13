import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1806
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int min = Integer.MAX_VALUE;
        // 인덱스끼리 빼면 될듯?
        int start = 0;
        int end = 0;

        while (start <= n && end <= n) {
            if (sum < s){
                sum += arr[end];
                end++;
            } else if (sum >= s) {
                min = Math.min(min, end - start);
                sum -= arr[start];
                start++;
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }
}
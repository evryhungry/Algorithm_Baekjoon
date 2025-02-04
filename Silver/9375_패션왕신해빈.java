import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/9375
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map = new HashMap<>();

            for (int j = 0; j < m; j++) {
                String[] s = br.readLine().split(" ");
                map.put(s[1], map.getOrDefault(s[1], 0) + 1);
            }

            int result = 1;
            for (int value : map.values()) {
                result *= (value + 1);
            }

            sb.append(result - 1).append("\n");
        }

        System.out.println(sb);

        // System.out.println(map.values().stream().reduce(1, (a, b) -> a * b) - 1);
    }
}

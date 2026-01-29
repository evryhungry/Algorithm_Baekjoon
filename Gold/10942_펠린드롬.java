//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.StringTokenizer;
//import java.lang.StringBuilder;
//
//
// //// https://www.acmicpc.net/problem/10942
// 시간 초과
//public class Main {
//    static int N;
//    static List<Integer> arr;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        N = Integer.parseInt(br.readLine());
//
//        arr = new ArrayList<>(
//                Arrays.stream(br.readLine().trim().split("\\s+"))
//                        .map(Integer::parseInt)
//                        .collect(java.util.stream.Collectors.toList())
//        );
//
//        int C = Integer.parseInt(br.readLine());
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < C; i++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            int S = Integer.parseInt(st.nextToken()) - 1;
//            int E = Integer.parseInt(st.nextToken()) - 1;
//
//            if (isPalindrome(S, E)) {
//                sb.append(1).append('\n');
//            } else {
//                sb.append(0).append('\n');
//            }
//        }
//
//        bw.write(sb.toString());
//        bw.flush();
//        bw.close();
//        br.close();
//    }
//
//    static boolean isPalindrome(int S, int E) {
//        while (S < E) {
//            if (!arr.get(S).equals(arr.get(E))) {
//                return false;
//            }
//            S++;
//            E--;
//        }
//        return true;
//    }
//}

import java.io.*;

public class Main {
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do { c = readByte(); } while (c <= ' ');
            int sign = 1;
            if (c == '-') { sign = -1; c = readByte(); }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int N = fs.nextInt();
        int[] a = new int[N + 1];
        for (int i = 1; i <= N; i++) a[i] = fs.nextInt();

        boolean[][] dp = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) dp[i][i] = true;

        for (int i = 1; i < N; i++) dp[i][i + 1] = (a[i] == a[i + 1]);

        for (int len = 3; len <= N; len++) {
            for (int s = 1; s + len - 1 <= N; s++) {
                int e = s + len - 1;
                dp[s][e] = (a[s] == a[e]) && dp[s + 1][e - 1];
            }
        }

        int M = fs.nextInt();
        StringBuilder sb = new StringBuilder(M * 2);
        for (int i = 0; i < M; i++) {
            int s = fs.nextInt();
            int e = fs.nextInt();
            sb.append(dp[s][e] ? '1' : '0').append('\n');
        }

        System.out.print(sb.toString());
    }
}


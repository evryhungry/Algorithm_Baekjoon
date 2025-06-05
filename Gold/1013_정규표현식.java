import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1013
public class Main {
    static int n;
    static String s ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            s = br.readLine();
            int s_length = s.length();
            int j = 0;

            while (j < s_length) {
                if (j + 2 <= s_length && s.substring(j, j + 2).equals("01")) {
                    j += 2;
                    continue;
                }

                if (j + 3 <= s_length && s.substring(j, j + 3).equals("100")) {
                    j += 3;
                    while (j < s_length && s.charAt(j) == '0') {
                        j++;
                    }
                    if (j >= s_length || s.charAt(j) != '1') {
                        j = -1;
                        break;
                    }
                    while (j < s_length && s.charAt(j) == '1') {
                        j++;
                        if (j + 2 < s_length && s.charAt(j + 1) == '0' && s.charAt(j + 2) == '0') break;
                    }
                    continue;
                }

                break;
            }

            if (j == s_length) sb.append("YES\n");
            else sb.append("NO\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
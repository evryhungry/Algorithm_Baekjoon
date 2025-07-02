import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/18917
// B126
public class Main {
    static int n ;
    static int num = 0;
    static long sum = 0;
    static long xor = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());

            switch (num) {
                case 1:
                    int val1 = Integer.parseInt(st.nextToken());
                    sum += val1;
                    xor = xor ^ val1;
                    break;
                case 2:
                    int val2 = Integer.parseInt(st.nextToken());
                    sum -= val2;
                    xor = xor ^ val2;
                    break;
                case 3:
                    bw.write(sum + "\n");
                    break;
                case 4:
                    bw.write(xor + "\n");
                    break;
            }
        }
        bw.flush();
    }
}
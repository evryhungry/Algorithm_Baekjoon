import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/6064
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            sb.append(kaing(m, n, x, y)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static int gcd (int m, int n){
        while (n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return m;
    }

    static int kaing(int m, int n, int x, int y){
        int last_year = m * n / gcd(m, n);

        int result = 0;
        while (last_year >= (x + result)){
            if ((x + result) % n == (y == n ? 0 : y)) return x + result;
            result += m;
        }

        return -1;
    }
}

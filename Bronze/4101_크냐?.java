import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/4101
public class Main {
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;
            else if (n > m) System.out.println("Yes");
            else System.out.println("No");
        }
    }
}
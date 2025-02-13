import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/17626
public class Main {
    static int[] db = new int[50001];
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        db[0] = 0;
        db[1] = 1;

        for (int i = 0; i <= n; i++) db[i] = i;

        for (int i = 0 ; i <= n; i++){
            for (int j = 1 ; (j * j) <= i; j++) db[i] = Math.min(db[i], db[i - (j * j)] + 1);
        }

        System.out.println(db[n]);
    }
}

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14928
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int mod = 20000303;

        String n = br.readLine();

        long remainder = 0;

        for (int i = 0; i < n.length(); i++) {
            int x = n.charAt(i) - '0';
            remainder = (remainder * 10 + x) % mod;
        }

        System.out.println(remainder);
    }
}
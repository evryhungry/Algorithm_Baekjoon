import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

// https://www.acmicpc.net/problem/3052
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            int n = Integer.parseInt(br.readLine());
            set.add(n % 42);
        }

        System.out.println(set.size());
    }
}

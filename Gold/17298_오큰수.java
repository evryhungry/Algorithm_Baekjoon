import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

// https://www.acmicpc.net/problem/17298
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] list = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] result = new int[N];

        Stack<Integer> stack = new Stack<>();

        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= list[i]) stack.pop();
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(list[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(int num : result) {
            sb.append(num).append(" ");
        }

        bw.write(sb.toString().trim());
        bw.flush();
        bw.close();
    }
}

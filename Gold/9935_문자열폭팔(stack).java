import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/9935
public class Main {
    static String input;
    static String boom;
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        input = br.readLine();
        boom = br.readLine();

        int boom_size = boom.length();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            stack.push(c);

            if (stack.size() >= boom_size) {
                boolean frag = true;

                for (int j = 0 ; j < boom_size ; j++) {
                    if (stack.get(stack.size() - boom_size + j) != boom.charAt(j)){
                        frag = false;
                        break;
                    }
                }

                if (frag) {
                    for (int k = 0 ; k < boom_size ; k++) {
                        stack.pop();
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            for (char c : stack) {
                output.append(c);
            }
            System.out.println(output);
        }
    }
}
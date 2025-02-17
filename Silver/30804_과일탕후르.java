import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/30804
public class Main {
    static int n, cnt, type;
    static int[] arr = new int[10];
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (n --> 0){
            int number = Integer.parseInt(st.nextToken());

            q.add(number);

            if (arr[number]++ == 0){
                type++;
            }

            while (type > 2){
                number = q.poll();

                if (--arr[number] == 0){
                    type--;
                }
            }

            cnt = Math.max(cnt, q.size());
        }

        System.out.println(cnt);
    }
}

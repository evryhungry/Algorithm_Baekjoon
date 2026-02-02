import java.io.*;
import java.util.*;

//https://www.acmicpc.net/problem/2473
public class Main {
    static int N;
    static List<Long> list;
    static long[] a = new long[3];
    static Long bestAns = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Long.parseLong(st.nextToken()));
        }

        Collections.sort(list);

        for (int i = 0; i < N - 2; i++) {
            int l = i + 1;
            int r = N - 1;

            while (l < r) {
                long sum = list.get(l) + list.get(r) + list.get(i);
                long absSum = Math.abs(sum);

                if (absSum < bestAns) {
                    bestAns = absSum;
                    a[0] = list.get(i);
                    a[1] = list.get(l);
                    a[2] = list.get(r);
                }

                if (sum > 0) r--;
                else l++;
            }
        }

        bw.write(a[0] + " " + a[1] + " " + a[2] + "\n");
        bw.flush();
        br.close();
    }
}
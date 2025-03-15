import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2096
public class Main {
    static String[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        list = new String[n];
        list[0] = "  *  ";
        list[1] = " * * ";
        list[2] = "*****";

        StringBuilder sb = new StringBuilder();
        if (n == 3) {
            sb.append(list[0] + "\n" + list[1] + "\n" + list[2] + "\n");
            bw.write(sb.toString());
            bw.flush();
            return;
        }

        for (int i = 1; (3 * Math.pow(2, i)) <= n; i++) {
            StartList(i);
        }

        for (int i = 0 ; i < n; i++) {
            sb.append(list[i] + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void StartList(int i){
        int bottom = (int) (3 * Math.pow(2, i));
        int mid = bottom / 2;

        for (int j = mid ; j < bottom; j++) {
            list[j] = list[j-mid] + " " + list[j-mid];
        }

        String blank = " ".repeat(mid); // Repeat 이라는 함수가 있었다
        for (int j = 0; j < mid ; j++){
            list[j] = blank + list[j] + blank;
        }

    }
}

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/13172
public class Main {
    static int p = 1000000007;
    static int c ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        c = Integer.parseInt(br.readLine());

        StringTokenizer st;
        long N = 1, S = 0;
        for (int i = 0; i < c ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            S = n * S + N * s;
            N *= n;

            S %= p;
            N %= p;
        }

        if(S % N != 0){
            // S/N이 정수가 아닐 때 (모듈러 연산으로 분수 계산)
            // N^(P-2) mod P 가 N의 모듈러 역원
            bw.write((search(N, p-2) * S) % p + "");
        } else{
            bw.write(S / N + "");
        }
        bw.flush();
        bw.close();
    }

    static long search(long n, int index){
        if (index == 1) {
            return n;
        }
        long temp = search(n, index / 2);
        if (index % 2 == 1)
            return temp * temp % p * n % p;
        else
            return temp * temp % p;
    }
}
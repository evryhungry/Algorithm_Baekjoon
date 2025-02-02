import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/5525
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String s = br.readLine();


        int result = 0;
        int count = 0;
        for (int i = 1; i < m-1 ; i++){
            if(s.charAt(i-1)=='I' && s.charAt(i)=='O' && s.charAt(i+1)=='I'){
                count++;
                i++;
                if (count == n){
                    result++;
                    count--;
                }
            } else count = 0;
        }

        System.out.println(result);
    }
}

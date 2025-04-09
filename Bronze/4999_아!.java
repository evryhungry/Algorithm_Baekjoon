import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/4999
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println( br.readLine().length() >= br.readLine().length() ? "go" : "no");
    }
}
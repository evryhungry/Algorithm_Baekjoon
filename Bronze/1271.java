import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

/**
 * 맞은 문제
 * 사유 !
 * 1. Integer type으로 나와야한다
 * 2. 단위가 매우 크다. (1 ≤ m ≤ n ≤ 101000, m과 n은 10진수 정수)
 * 문제의 답안에서 소수점이 나오면 틀리게 됨.
 */

public class Main {

    static float A, B;
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        A = Float.parseFloat(st.nextToken());
        B = Float.parseFloat(st.nextToken());
        System.out.println(A / B);
        System.out.println(A % B);

    }

}
/**
 * 맞은 문제
 * 사유 !
 * 1. Integer type으로 나와야한다
 * 2. 단위가 매우 크다. (1 ≤ m ≤ n ≤ 101000, m과 n은 10진수 정수)
 * 3. BigInteger는 자바에서 기본 데이터 타입을 넘어서는 큰 수로 연산할 때 쓰이는 math 패키지의 클래스로, 기본으로 제공하는 메서드 종류가 많아서 활용도가 높다. 이 문제에서는 나누기 메서드인 divide()와 몫을 구하는 메서드인 remainder()를 썼다.
 */

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger a = sc.nextBigInteger();
        BigInteger b = sc.nextBigInteger();
        sc.close();
        System.out.println(a.divide(b));
        System.out.println(a.remainder(b));
    }
}
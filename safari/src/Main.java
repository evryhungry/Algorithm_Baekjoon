import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] var0) {
        Scanner var1 = new Scanner(System.in);
        long n = var1.nextLong();
        long m = var1.nextLong();

        long sum =  Math.abs(n-m);
        System.out.print(sum);
    }
}
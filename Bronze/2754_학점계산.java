import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2754
public class Main {

    public enum Grade {
        A_PLUS("A+", 4.3),
        A0("A0", 4.0),
        A_MINUS("A-", 3.7),

        B_PLUS("B+", 3.3),
        B0("B0", 3.0),
        B_MINUS("B-", 2.7),

        C_PLUS("C+", 2.3),
        C0("C0", 2.0),
        C_MINUS("C-", 1.7),

        D_PLUS("D+", 1.3),
        D0("D0", 1.0),
        D_MINUS("D-", 0.7),

        F("F", 0.0);

        private final String code;
        private final double point;

        Grade(String code, double point) {
            this.code = code;
            this.point = point;
        }

        public static Grade fromCode(String code) {
            for (Grade g : Grade.values()) {
                if (g.code.equals(code)) {
                    return g;
                }
            }
            throw new IllegalArgumentException("Invalid grade code: " + code);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        System.out.println(Grade.fromCode(s).point);
    }
}
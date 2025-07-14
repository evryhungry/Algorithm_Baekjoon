import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/4659
// 1600 - 1618
public class Main {
    static boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'o' || c == 'u' || c == 'i';
    }
    static boolean isValid;
    static char prevChar = '.';
    static int count ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        String line;
        while (!(line = br.readLine()).equals("end")) {
            isValid = false;
            count = 0;

            for (char c : line.toCharArray()) {
                if (isVowel(c)) isValid = true;

                if (isVowel(c) != isVowel(prevChar)) count = 1;
                else count++;

                if (count > 2 || (prevChar == c && (prevChar != 'e' && prevChar != 'o'))){
                    isValid = false;
                    break;
                }

                prevChar = c;
            }

            if (isValid) {
                sb.append("<" + line + "> is acceptable.\n");
            } else {
                sb.append("<" + line + "> is not acceptable.\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
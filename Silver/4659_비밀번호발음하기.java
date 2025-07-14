import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/4659
// 1600 - 1618
public class Main {
    static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    static boolean isVowel(char c){
        for (char vowel : vowels){
            if (c == vowel){
                return true;
            }
        }
        return false;
    }
    static char prevChar = 0;
    static int vowelCount = 0;
    static int consonantCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        while (true) {
            boolean isValid = true;
            String line = br.readLine();
            if (line.equals("end")) break;

            for (char c : line.toCharArray()) {
                if (prevChar == c) {
                    if (c != 'e' && c != 'o') {
                        isValid = false;
                        break;
                    }
                }
                if (isVowel(c)) {
                    consonantCount = 0;
                    vowelCount++;
                } else {
                    vowelCount = 0;
                    consonantCount++;
                }
                if (vowelCount >= 3 || consonantCount >= 3) {
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
import java.io.*;
import java.util.*;

/**
 *  문자열을 수정하거나 연결해야 한다면, 문자열 변경이 많을 때 효율적 → StringBuilder
 *  문자열을 특정 구분자로 나누고 싶다면, 단순 문자열 파싱 작업에 적합 → StringTokenizer (또는 String.split())
 *
 *  question: 1032
 *  시작 -> 실행 -> cmd를 쳐보자. 검정 화면이 눈에 보인다. 여기서 dir이라고 치면 그 디렉토리에 있는 서브디렉토리와 파일이 모두 나온다. 이때 원하는 파일을 찾으려면 다음과 같이 하면 된다.
 *  dir *.exe라고 치면 확장자가 exe인 파일이 다 나온다. "dir 패턴"과 같이 치면 그 패턴에 맞는 파일만 검색 결과로 나온다.
 *  예를 들어, dir a?b.exe라고 검색하면 파일명의 첫 번째 글자가 a이고, 세 번째 글자가 b이고, 확장자가 exe인 것이 모두 나온다.
 *  이때 두 번째 문자는 아무거나 나와도 된다.예를 들어, acb.exe, aab.exe, apb.exe가 나온다.
 *  이 문제는 검색 결과가 먼저 주어졌을 때, 패턴으로 뭘 쳐야 그 결과가 나오는지를 출력하는 문제이다. 패턴에는 알파벳과 "." 그리고 "?"만 넣을 수 있다.
 *  가능하면 ?을 적게 써야 한다. 그 디렉토리에는 검색 결과에 나온 파일만 있다고 가정하고, 파일 이름의 길이는 모두 같다.
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] dir = new String[n];
        for (int i = 0; i < n; i++) {
            dir[i] = br.readLine();
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < dir[0].length(); i++) {
            boolean check = true;
            char c = dir[0].charAt(i);

            for (int j = 1; j < n; j++) if (dir[j].charAt(i) != c) check = false;


            if (check) {
                sb.append(c);
            } else {
                sb.append("?");
            }
        }

        System.out.println(sb);
    }
}
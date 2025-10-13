/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/155652
 * 둘만의 암호
 * 20분
 * 문제해석:
 * 1. 문자열 s가 주어진다.
 * 2. 문자열 skip이 주어진다.
 * 3. index가 주어진다.
 * 4. s의 각 문자를 index만큼 뒤의 문자로 바꾼다.
 * 5. 단, skip에 있는 문자는 제외한다.
 * 6. 알파벳은 a~z까지 순환한다.
 * 7. z를 넘어가면 다시 a로 돌아간다.
 * 8. 바뀐 문자열을 return 한다.
 *
 * 풀이:
 * 1. skip 문자열을 리스트에 담는다.
 * 2. s 문자열을 순회하면서 각 문자를 index만큼 뒤의 문자로 바꾼다.
 *    - 2-1. 현재 문자에서 1씩 더하면서 index만큼 이동한다.
 *    - 2-2. z를 넘어가면 다시 a로 돌아간다.
 * 3. index만큼 뒤의 문자가 skip에 있으면 건너뛴다.
 * 4. z를 넘어가면 다시 a로 돌아간다.
 * 5. 바뀐 문자열을 StringBuilder에 담는다.
 * 6. StringBuilder를 문자열로 변환하여 return 한다.
 */

import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        List<Character> char_skip = new ArrayList<>();
        for (char ch : skip.toCharArray()) {
            char_skip.add(ch);
        }

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            int jump = 0;
            char current = c;
            while (jump < index) {
                current++;
                if (current > 'z') current = 'a';

                if (!char_skip.contains(current)) jump++;
            }
            sb.append(current);
        }

        return sb.toString();
    }
}
// https://school.programmers.co.kr/learn/courses/30/lessons/60058

/**
 *  
 * Solution
 * 
 * 처음 풀때 stack으로 풀었는데, 바로 닫아버러서 틀린 이슈로 인해 stack으로 풀지 않고, 
 * 균형잡힌 괄호 문자열을 나누고, 올바른 괄호 문자열인지 확인하는 방식으로 구현함.
 * 
 * 걸린 시간 : 1h
 * 
 * 
 */

class Solution {
    public String solution(String p) {
        if (p.isEmpty()) return "";

        int open = 0, close = 0;
        int idx = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') open++;
            else close++;
            if (open == close) {
                idx = i;
                break;
            }
        }

        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);

        if (isCorrect(u)) {
            return u + solution(v);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
            sb.append(solution(v));
            sb.append(')');

            for (int i = 1; i < u.length() - 1; i++) {
                sb.append(u.charAt(i) == '(' ? ')' : '(');
            }

            return sb.toString();
        }
    }

    private boolean isCorrect(String u) {
        int balance = 0;
        for (char c : u.toCharArray()) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return true;
    }
}
// https://school.programmers.co.kr/learn/courses/30/lessons/12951
// 1 : 2050-2104
class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isStart = true;

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append(c);
                isStart = true;
            } else {
                if (isStart) {
                    sb.append(Character.toUpperCase(c));
                    isStart = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }

        return sb.toString();
    }
}

// 2. 진짜 똑똑한 사람들 코드를 한번 다시 만들어 보았다.
class Solution {
    public String solution(String s) {
        String answer = "";
        String[] sp = s.toLowerCase().split("");
        boolean isBlank = true;

        for (String ss : sp){
            answer += isBlank ? ss.toUpperCase() : ss;
            isBlank = ss.equals(" ") ? true : false;
        }

        return answer;
    }
}
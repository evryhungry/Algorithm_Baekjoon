/**
 * 프로그래머스 - 문자열 나누기
 * https://school.programmers.co.kr/learn/courses/30/lessons/140108
 *  문제해석:
 *  1. 문자열 s를 특정 조건에 맞게 여러 개의 문자열로 나누는 문제
 *  2. 조건:
 *   1) 첫 글자를 x라고 정한다.
 *   2) 문자열을 왼쪽부터 읽어나가면서 x와 같은 글자가 나올 때마다 x_count를 증가시키고,
 *   x와 다른 글자가 나올 때마다 other_count를 증가시킨다.
 *   3) x_count와 other_count가 같아지는 순간 문자열을 나눈다.
 *   4) s의 모든 글자를 읽을 때까지 1~3의 과정을 반복한다.
 *   5) 만약 s의 모든 글자를 읽었을 때, x_count와 other_count가 같지 않다면
 *   나머지 글자들을 하나의 문자열로 나눈다.
 *
 *   풀이:
 *   1. 문자열 s를 char 배열로 변환한다.
 *   2. 첫 글자를 first 변수에 저장한다.
 *   3. first_count와 other_count 변수를 각각 0으로 초기화한다.
 *   4. char 배열을 순회하면서 first와 같은 글자가 나오면 first_count를 증가시키고,
 *   다른 글자가 나오면 other_count를 증가시킨다.
 *   5. first_count와 other_count가 같아지는 순간 answer 변수를 증가시키고,
 *   다음 글자를 first로 설정하고 first_count와 other_count를 0으로 초기화한다.
 *   6. 모든 글자를 순회한 후, first_count와 other_count가 같지 않다면 answer 변수를 하나 증가시킨다.
 *   7. answer 변수를 return 한다.
 *
 *   시간복잡도 O(n), 공간복잡도 O(1)
 *   8. 푸는데 걸린 시간 : 15분
 */

package programmers;

class Solution {
    static char first;
    static int first_count ;
    static int other_count ;

    public int solution(String s) {
        int answer = 0;
        char[] s_array = s.toCharArray();
        first = s.charAt(0);
        first_count = 0;
        other_count = 0;

        for (int i = 0; i < s_array.length ; i++){
            if (first_count == 0 || first == s_array[i]){
                first_count++;
            } else if (first != s_array[i]){
                other_count++;
            }

            if (first_count == other_count){
                answer++;
                if (i + 1 < s_array.length){
                    init(s_array[i+1]);
                }
            }
        }

        if (first_count != other_count) answer++;

        return answer;
    }

    static void init(char c){
        first = c;
        first_count = 0;
        other_count = 0;
    }
}
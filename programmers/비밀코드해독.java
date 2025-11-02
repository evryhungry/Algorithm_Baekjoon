/**
 * 비밀코드 해독
 * https://school.programmers.co.kr/learn/courses/30/lessons/388352
 * 문제해석:
 * 1. 1부터 n까지의 숫자 중에서 5개의 숫자를 고른다.
 * 2. q 배열에는 여러 개의 질문이 들어있다.
 * 3. 각 질문은 여러 개의 숫자로 이루어져 있다.
 * 4. ans 배열에는 각 질문에 대한 정답의 갯수가 들어있다.
 * 5. 고른 5개의 숫자 중에서 각 질문에 포함된 숫자의 갯수가 ans 배열의 값과 일치해야 한다.
 * 6. 가능한 5개의 숫자 조합의 갯수를 구하는 문제
 *
 * 풀이:
 * 1. 조합을 이용하여 1부터 n까지의 숫자 중에서 5개의 숫자를 고른다.
 * 2. 고른 5개의 숫자 조합이 각 질문에 대해 ans 배열의 값과 일치하는지 확인한다.
 * 3. 일치한다면 answer 변수를 증가시킨다.
 * 4. 모든 조합을 확인한 후 answer 변수를 return 한다.
 * -> 백트래킹 기법을 이용하여 조합을 구하고, 각 조합에 대해 질문을 확인하는 방식으로 구현
 * 시간복잡도 O(nC5 * m * k) nC5는 n개 중 5개를 고르는 조합의 수, m은 질문의 수, k는 각 질문에 포함된 숫자의 최대 개수
 *
 * 푸는데 걸린 시간: 30분
 * 아쉬운점: 시간 엄청 걸리는 풀이.. 더 효율적인 풀이 고민해보기
 */

package programmers;

import java.util.*;

class Solution {
    private static Set<Integer> set = new HashSet<>();
    private static boolean[] visited;
    private static int answer = 0;

    public int solution(int n, int[][] q, int[] ans) {
        visited = new boolean[n+1];

        setAnswerList(1, n+1, q, ans);

        return answer;
    }

    static void setAnswerList(int start, int last, int[][] q, int[] ans){
        if (set.size() == 5) {
            searchAnswer(q, ans);
            return;
        }

        for (int i = start; i < last; i++){
            if (!visited[i]) {
                set.add(i);
                visited[i] = true;
                setAnswerList(i + 1, last, q, ans);
                visited[i] = false;
                set.remove(i);
            }
        }
    }

    static void searchAnswer(int[][] q, int[] ans){
        for (int i = 0; i < q.length ; i++){
            int check = 0;
            for (int j : q[i]){
                if (set.contains(j)) check++;
            }
            if(check != ans[i]) return;
        }
        answer++;
    }
}

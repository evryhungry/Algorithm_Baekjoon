/**
 * 표 편집
 * https://school.programmers.co.kr/learn/courses/30/lessons/81303
 * 문제해석:
 * 1. n개의 행으로 이루어진 표가 있다.
 * 2. k번째 행이 선택되어 있다.
 * 3. cmd 배열에는 명령어들이 담겨있다.
 * 4. 명령어는 다음과 같다.
 *  - U X : X칸 위로 이동
 *  - D X : X칸 아래로 이동
 *  - C : 현재 선택된 행을 삭제하고, 바로 아래 행을 선택.
 *        만약, 삭제된 행이 마지막 행이라면 바로 위 행을 선택한다.
 *  - Z : 가장 최근에 삭제된 행을 복구한다. 복구된 행은 원래 있던 위치에 돌아간다.
 * 5. 모든 명령어를 수행한 후, 남아있는 행은 'O', 삭제된 행은 'X'로 표시한 문자열을 return 하라.
 *
 * 문제 해결 방법:
 * 1. 각 행의 이전 행과 다음 행에 대한 정보를 담은 배열을 만든다.
 * 2. 스택을 사용해 삭제한 행의 정보를 저장한다.
 * 3. StringBuilder를 사용해 결과 문자열을 만든다.
 * 4. 명령어를 하나씩 처리한다.
 * - U X : X칸 위로 이동
 * - D X : X칸 아래로 이동
 * - C : 현재 선택된 행을 삭제하고, 바로 아래 행을 선택.
 *      만약, 삭제된 행이 마지막 행이라면 바로 위 행을 선택한다.
 * - Z : 가장 최근에 삭제된 행을 복구한다. 복구된 행은 원래 있던 위치에 돌아간다.
 * 5. 모든 명령어를 처리한 후, 결과 문자열을 반환한다.
 * 6. 시간 복잡도는 O(m + n), 공간 복잡도는 O(n)이다.
 * 7. 걸린 시간 : 1h 10m
 */

import java.util.Stack;

class Solution {
    public class Node {
        int pre, cur, nxt;

        public Node(int pre, int cur, int nxt){
            this.pre = pre;
            this.cur = cur;
            this.nxt = nxt;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        int[] pres = new int[n];
        int[] nxts = new int[n];
        Stack<Node> stack = new Stack<>();
        StringBuilder sb = new StringBuilder("O".repeat(n));

        for(int i = 0 ; i < n; i++){
            pres[i] = i - 1;
            nxts[i] = i + 1;
        }
        nxts[n-1] = -1;

        for(String s : cmd){
            char c = s.charAt(0);
            if(c == 'U'){
                int num = Integer.parseInt(s.substring(2));
                while(num-- > 0) k = pres[k];
            } else if (c == 'D'){
                int num = Integer.parseInt(s.substring(2));
                while(num-- > 0) k = nxts[k];
            } else if (c == 'C'){
                stack.push(new Node(pres[k], k, nxts[k]));
                if(pres[k] != -1) nxts[pres[k]] = nxts[k];
                if(nxts[k] != -1) pres[nxts[k]] = pres[k];
                sb.setCharAt(k, 'X');

                // 만약 nxts[k] 가 -1이면 이전 것으로(없어졌기에), 아니면 다음 것으로.
                if(nxts[k] != -1) k = nxts[k];
                else k = pres[k];
            } else { // 'Z'인 경우
                Node node = stack.pop();
                if(node.pre != -1) nxts[node.pre] = node.cur;;
                if(node.nxt != -1) pres[node.nxt] = node.cur;;
                sb.setCharAt(node.cur, 'O');
            }
        }

        return sb.toString();
    }
}
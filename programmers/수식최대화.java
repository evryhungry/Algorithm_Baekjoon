/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/67257
 * 수식 최대화
 * 1h 10m
 * 문제 설명:
 * 1. 수식이 문자열로 주어진다.
 * 2. 수식은 숫자와 연산자로 이루어져 있다.
 * 3. 연산자는 +, -, * 세 가지이다.
 * 4. 연산자의 우선순위를 재정의하여 수식의 결과가 절대값일때, 최대가 되도록 하려고 한다.
 * 5. 수식의 결과가 최대가 될 때의 그 최대값을 return 하는 solution 함수를 완성해주세요.
 *
 * 풀이:
 * 1. 연산자의 우선순위를 정하는 모든 경우의 수를 구한다. (3! = 6가지)
 * 2. 각 우선순위에 따라 수식을 계산한다.
 * 3. 계산된 결과의 절대값을 구한다.
 * 4. 절대값이 최대인 값을 저장한다.
 * 5. 모든 우선순위를 다 계산한 후, 최대값을 return 한다.
 *
 * 시간복잡도 O(n^2), 공간복잡도 O(n)
 */

import java.util.*;

class Solution {
    static String[] op = {"+", "-", "*"};
    static boolean[] visited = new boolean[3];
    static String[] priority = new String[3];
    static ArrayList<String> opList = new ArrayList<String>();
    static ArrayList<Long> numList = new ArrayList<Long>();
    static Long result = 0L;

    public long solution(String expression) {
        int idx = 0;
        int eLength = expression.length();

        for (int i = 0 ; i < eLength; i++){
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*'){
                opList.add(c+"");
                numList.add(Long.parseLong(expression.substring(idx, i)));
                idx = i + 1;
            }
        }
        numList.add(Long.parseLong(expression.substring(idx, eLength)));

        dfs(0);

        return result;
    }

    static void dfs(int d){
        if(d == 3){
            searchMaxResult();
            return;
        }

        for (int i = 0 ; i < 3 ; i++){
            if (visited[i]) continue;
            visited[i] = true;
            priority[d] = op[i];
            dfs(d+1);
            visited[i] = false;
        }
    }

    static void searchMaxResult(){
        ArrayList<String> tempOp = new ArrayList<String>(opList);
        ArrayList<Long> tempNum = new ArrayList<Long>(numList);

        for (int i = 0; i < priority.length ; i++){
            String operater = priority[i];
            for (int j = 0 ; j < tempOp.size() ; j++){
                if(operater.equals(tempOp.get(j))){
                    long a = tempNum.get(j);
                    long b = tempNum.get(j+1);
                    long res = calculus(a, b, operater);

                    tempNum.remove(j+1); // 여기가 먼저 삭제되어야 했는데 실수..
                    tempNum.remove(j);
                    tempOp.remove(j);

                    tempNum.add(j, res);
                    j--;
                }
            }
        }

        result = Math.max(result, Math.abs(tempNum.get(0))); // 이 부분 절대값 실수.
    }

    static Long calculus (Long a, Long b, String operater){
        Long temp = 0L;
        switch (operater){
            case "+":
                temp = a + b;
                break;
            case "-":
                temp = a - b;
                break;
            case "*":
                temp = a * b;
                break;
        }
        return temp;
    }
}
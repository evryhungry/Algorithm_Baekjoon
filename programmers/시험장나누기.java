/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/81305
 * 시험장나누기
 * 2시간
 *
 * 문제해석:
 * 1. 트리 구조로 표현된 시험장이 있다.
 * 2. 각 시험장에는 응시자 수가 있다.
 * 3. 트리의 각 노드는 시험장을 나타내며, 노드의 값은 해당 시험장의 응시자 수를 나타낸다.
 * 4. 트리의 루트 노드에서 시작하여 자식 노드로 이동할 수 있다.
 * 5. 트리의 각 노드는 최대 두 개의 자식 노드를 가질 수 있다.
 * 6. 트리의 각 노드는 부모 노드와 자식 노드로 연결되어 있다.
 * 7. 트리의 각 노드는 -1로 표시된 자식 노드를 가질 수 있다.
 * 8. k개의 그룹으로 시험장을 나누려고 한다.
 * 9. 각 그룹의 응시자 수의 합이 최소가 되도록 나누려고 한다.
 * 10. 각 그룹의 응시자 수의 합이 최소가 되도록 나누었을 때, 가장 많은 응시자 수를 구하라.
 *
 * 1번째 풀이: => 1시간 30분 소요, 실패 why? 원인을 찾고 해결 방법을 떠올리지 못한 문제.
 * 1. DFS로 각 노드의 응시자 수의 합을 구한다
 * 2. 각 노드의 응시자 수의 합이 total_number/k 보다 크면 best 값을 갱신한다.
 * 3. best 값을 return 한다.
 * 4. 시간복잡도 O(n), 공간복잡도 O(n)
 * 5. 정답 처리 실패
 * 6. 이유: DFS로 탐색하는 순서에 따라 best 값이 달라질 수 있다.
 * 7. 따라서, DFS를 왼쪽 자식 노드부터 탐색하는 경우와 오른쪽 자식 노드부터 탐색하는 경우를 모두 고려해야 한다.
 * 8. 그러나, 특정 부분에서 탐색 순서에 따라 best 값이 달라지는 경우가 존재한다.
 *
 * 2번째 풀이: => 30분 소요, 성공 why? 이분 탐색을 사용하여 각 그룹의 응시자 수의 합의 최솟값을 구하는 방법을 떠올림. (중요 포인트: 이분탐색)
 * 1. 이분 탐색으로 각 그룹의 응시자 수의 합의 최솟값을 구한다.
 * 2. 각 노드의 응시자 수의 합이 mid 보다 크면 그룹을 나눈다.
 * 3. 그룹의 개수가 k보다 작거나 같으면 답을 갱신하고, mid 값을 줄인다.
 * 4. 그룹의 개수가 k보다 크면 mid 값을 늘린다.
 * 5. 최종적으로 구한 답을 return 한다.
 * 6. 시간복잡도 O(n log m), 공간복잡도 O(n)
 * 7. 정답 처리 성공
 * 8. 이유: 이분 탐색으로 각 그룹의 응시자 수의 합의 최솟값을 구하기 때문에 탐색 순서에 영향을 받지 않는다.
 * 9. 따라서, 항상 최적의 답을 구할 수 있다.
 * 10. 이분 탐색의 범위는 각 노드의 응시자 수의 최댓값부터 전체 응시자 수의 합까지이다.
 * 11. 각 노드의 응시자 수의 최댓값은 반드시 한 그룹에 포함되어야 하기 때문이다.
 * 12. Gemini 사용.
 *
 */


 import java.util.*;

 class Solution {
     static int total_number;
     static int best = Integer.MAX_VALUE;
     static int k;

     public int solution(int k, int[] num, int[][] links) {
         this.k = k;
         total_number = 0;
         best = Integer.MAX_VALUE;

         Node[] nodes = build(num, links);
         int root = findRoot(links);

         dfsSumL(root, nodes);
         dfsSumR(root, nodes);

         return best;
     }

     static Node[] build(int[] num, int[][] links) {
         int n = links.length;
         Node[] nodes = new Node[n];
         for (int i = 0; i < n; i++) {
             nodes[i] = new Node(i, num[i]);
             total_number += num[i];
         }

         for (int i = 0; i < n; i++) {
             nodes[i].left  = links[i][0];
             nodes[i].right = links[i][1];
         }
         return nodes;
     }

     static int findRoot(int[][] links) {
         int n = links.length;
         int[] parent = new int[n];
         Arrays.fill(parent, -1);
         for (int p = 0; p < n; p++) {
             int L = links[p][0], R = links[p][1];
             if (L != -1) parent[L] = p;
             if (R != -1) parent[R] = p;
         }
         for (int i = 0; i < n; i++) if (parent[i] == -1) return i;
         return -1;
     }

     static int dfsSumL(int cur, Node[] nodes) {
         if (cur == -1) return 0;
         int L = dfsSumL(nodes[cur].left, nodes);
         int R = dfsSumL(nodes[cur].right, nodes);
         int sub = L + R + nodes[cur].value;

         if (k * sub > total_number / k) {
             if (sub < best) {
                 best = sub;
                 return 0;
             }
         }

         return sub;
     }

     static int dfsSumR(int cur, Node[] nodes) {
         if (cur == -1) return 0;
         int R = dfsSumR(nodes[cur].right, nodes);
         int L = dfsSumR(nodes[cur].left, nodes);
         int sub = L + R + nodes[cur].value;

         if (k * sub > total_number) {
             if (sub < best) {
                 best = sub;
                 return 0;
             }
         }

         return sub;
     }


     static class Node{
         int me;
         int value;
         int left;
         int right;

         Node(int me, int value) {
             this.me = me;
             this.value = value;
             this.left = -1;
             this.right = -1;
         }
     }
 }

class Solution {

    private int answer = Integer.MAX_VALUE;
    private int K;
    private Node[] nodes;
    private int count;

    static class Node {
        int id;
        int value;
        int left;
        int right;

        Node(int id, int value) {
            this.id = id;
            this.value = value;
            this.left = -1;
            this.right = -1;
        }
    }

    public int solution(int k, int[] num, int[][] links) {
        this.K = k;

        int N = num.length;
        nodes = new Node[N];
        long totalSum = 0;
        int maxVal = 0;

        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i, num[i]);
            totalSum += num[i];
            maxVal = Math.max(maxVal, num[i]);
        }

        for (int i = 0; i < N; i++) {
            nodes[i].left = links[i][0];
            nodes[i].right = links[i][1];
        }

        int root = findRoot(links, N);

        long L = maxVal;
        long R = totalSum;

        while (L <= R) {
            int mid = (int) (L + R) / 2;

            if (check(root, mid)) {
                answer = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        return answer;
    }

    private int findRoot(int[][] links, int n) {
        boolean[] isChild = new boolean[n];
        for (int[] link : links) {
            if (link[0] != -1) isChild[link[0]] = true;
            if (link[1] != -1) isChild[link[1]] = true;
        }
        for (int i = 0; i < n; i++) {
            if (!isChild[i]) return i;
        }
        return -1;
    }

    private boolean check(int root, int limit) {
        count = 0;
        int remainingSum = dfs(root, limit);
        count++;
        return count <= K;
    }

    private int dfs(int cur, int limit) {
        if (cur == -1) return 0;

        int leftSum = dfs(nodes[cur].left, limit);
        int rightSum = dfs(nodes[cur].right, limit);

        int value = nodes[cur].value;
        int totalSum = value + leftSum + rightSum;

        if (totalSum > limit) {
            if (leftSum > rightSum) {
                count++;

                int remainingSum = value + rightSum;
                if (remainingSum > limit) {
                    count++;
                    return value;
                }
                return remainingSum;
            } else {
                count++;

                int remainingSum = value + leftSum;
                if (remainingSum > limit) {
                    count++;
                    return value;
                }
                return remainingSum;
            }
        }
        return totalSum;
    }
}
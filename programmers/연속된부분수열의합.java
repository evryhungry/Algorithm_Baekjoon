package programmers;

/**
 * idx가 작은 곳부터 큰곳까지, 순환하면서 합쳤을떄, 뺐을 때 값을 확인하며 풀수있다.
 *
 * 2-pointer 문제.
 *
 * 제한사항:
 * 5 ≤ sequence의 길이 ≤ 1,000,000
 *     1 ≤ sequence의 원소 ≤ 1,000
 *     sequence는 비내림차순으로 정렬되어 있습니다.
 * 5 ≤ k ≤ 1,000,000,000
 *     k는 항상 sequence의 부분 수열로 만들 수 있는 값입니다.
 *
 * 풀이:
 * - right와 left의 index를 움직이며 풀어낼 수 있었음.
 * - sum이 같으면 minLen보다 작인지를 우선 확인하고
 *      - 작으면 해당 index를 주입.
 *      - 그렇지 않으면 left를 옮긴다.
 *          왜 left만 옮기는가? -> 더 큰 인덱스에서 같은 합이 있는지 찾기 위함이지 아무래도~
 */
class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0, right = 0;
        int sum = sequence[0];

        int[] answer = new int[2];
        int minLen = 10000001;

        while (right < sequence.length) {
            if (sum == k) {
                if (right - left < minLen) {
                    minLen = right - left;
                    answer[0] = left;
                    answer[1] = right;
                }
                sum -= sequence[left++];
            } else if (sum < k) {
                right++;
                if (right < sequence.length) {
                    sum += sequence[right];
                }
            } else {
                sum -= sequence[left++];
            }
        }

        return answer;
    }
}

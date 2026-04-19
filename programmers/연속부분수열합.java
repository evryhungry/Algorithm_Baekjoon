package programmers;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

// 접근 방식: 누적합 + Set(중복 제거)
// 아쉬운점 : 2차배열로 풀었다는 것 -> memory 사용량이 큼. n^2 이여도 공간복잡도 아쉽.. O(n^2)
class Solution {
    private int[][] bf ;

    public int solution(int[] elements) {
        int elements_size = elements.length;
        Set<Integer> s = new HashSet<>();
        bf = new int[elements_size][elements_size];

        for (int r = 0 ; r < elements_size ; r++){
            if(r == elements_size - 1) {
                s.add(Arrays.stream(elements).sum());
                break;
            }


            for (int c = 0 ; c < elements_size ; c++){
                if (r == 0){
                    bf[r][c] = elements[c];
                    s.add(elements[c]);
                }  else if( r == 1){
                    if (c == elements_size - 1) {
                        bf[r][c] = bf[r - 1][c] + bf[r - 1][0];
                    } else {
                        bf[r][c] = bf[r - 1][c] + bf[r - 1][(c + 1) % elements_size];
                    }

                    s.add(bf[r][c]);
                } else {
                    if (c == elements_size - 1){
                        bf[r][c] = bf[r - 1][c] + bf[r - 1][0] -  bf[r - 2][0];
                    } else {
                        bf[r][c] = bf[r - 1][c] + bf[r - 1][(c + 1) % elements_size] - bf[r - 2][(c + 1) % elements_size];
                    }

                    s.add(bf[r][c]);
                }
            }
        }

        return s.size();
    }
}

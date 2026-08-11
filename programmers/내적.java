// ㅎㅎ..
class Solution {
    public int solution(int[] a, int[] b) {
        return java.util.stream.IntStream.range(0, a.length).map(i -> a[i] * b[i]).sum();
    }
}
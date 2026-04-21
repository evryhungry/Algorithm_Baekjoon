package programmers;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];

        for (int i = 1 ; i < arrayA.length ; i++){
            gcdA = gcd(gcdA, arrayA[i]);
            gcdB = gcd(gcdB, arrayB[i]);
        }

        gcdA = checkDivide(gcdA, arrayB);
        gcdB = checkDivide(gcdB, arrayA);

        if (gcdA == 0 && gcdB == 0) return 0;
        return gcdA >= gcdB ? gcdA : gcdB ;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    private int checkDivide(int g, int[] array){
        for(int i = 0 ; i < array.length ; i++){
            if(array[i] % g == 0) return 0;
        }

        return g;
    }
}
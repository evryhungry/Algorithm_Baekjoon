import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2143
public class Main {
    static long t;
    static int n, m;
    static int aIndex, bIndex, aMax, bMax;
    static long[] aSum, bSum;
    static int[] aList, bList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Long.parseLong(br.readLine());
        n = Integer.parseInt(br.readLine());
        aList = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            aList[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        bList = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            bList[i] = Integer.parseInt(st.nextToken());
        }

        aMax = n*(n+1)/2;
        aSum = new long[aMax];
        for(int i=0; i<n; i++){
            int av = 0;
            for( int j=i; j<n; j++){
                av += aList[j];
                aSum[aIndex++] = av;
            }
        }

        bMax = m*(m+1)/2;
        bSum = new long[bMax];
        for(int i=0; i<m; i++){
            int bv = 0;
            for( int j=i; j<m; j++){
                bv += bList[j];
                bSum[bIndex++] = bv;
            }
        }

        Arrays.sort(aSum);
        Arrays.sort(bSum);
        int left = 0, right = bMax-1;
        long ans = 0;
        while(left<aMax && right>-1){
            long currentA = aSum[left];
            long currentB = bSum[right];
            long sum = currentA + currentB;
            if(sum == t){
                long aCount = 0, bCount = 0;
                while (left<aMax && aSum[left] == currentA){
                    aCount++;
                    left++;
                }
                while (right>=0 && bSum[right] == currentB){
                    bCount++;
                    right--;
                }
                ans += aCount * bCount;
            }
            else if (sum > t) right--;
            else if (sum < t) left++;
        }
        System.out.println(ans);
    }
}

import java.io.*;
import java.util.*;

/**
 * 문제: 1717_집합의표현
 * 초기에 $n+1$개의 집합 $\{0\}, \{1\}, \{2\}, \dots , \{n\}$이 있다. 여기에 합집합 연산과,
 * 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하려고 한다.
 * 집합을 표현하는 프로그램을 작성하시오.
 * 입력 :
 * 첫째 줄에 $n$, $m$이 주어진다. $m$은 입력으로 주어지는 연산의 개수이다.
 * 다음 $m$개의 줄에는 각각의 연산이 주어진다.
 * 합집합은 $0$ $a$ $b$의 형태로 입력이 주어진다.
 * 이는 $a$가 포함되어 있는 집합과, $b$가 포함되어 있는 집합을 합친다는 의미이다.
 * 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산은 $1$ $a$ $b$의 형태로 입력이 주어진다.
 * 이는 $a$와 $b$가 같은 집합에 포함되어 있는지를 확인하는 연산이다.
 */
public class Main {
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parents = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int check = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (check == 0){
                union(a,b);
            }
            else {
                if (connected(a, b)){
                    sb.append("YES\n");
                }
                else {
                    sb.append("NO\n");
                }
            }
        }
        System.out.println(sb);
        br.close();
    }


    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]); // 경로 압축
    }

    static void union(int x, int y) {
        int a = find(x);
        int b = find(y);
        if(a > b){
            parents[b] = a;
        }
        else{
            parents[a] = b;
        }
    }

    static boolean connected(int a, int b) {
        return find(a) == find(b);
    }
}

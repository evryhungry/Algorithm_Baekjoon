import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 문제: 4195_친구 네트워크
 * 민혁이는 소셜 네트워크 사이트에서 친구를 만드는 것을 좋아하는 친구이다. 우표를 모으는 취미가 있듯이, 민혁이는 소셜 네트워크 사이트에서 친구를 모으는 것이 취미이다.
 * 어떤 사이트의 친구 관계가 생긴 순서대로 주어졌을 때, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.
 * 친구 네트워크란 친구 관계만으로 이동할 수 있는 사이를 말한다.
 *
 * 입력:
 * 첫째 줄에 테스트 케이스의 개수가 주어진다. 각 테스트 케이스의 첫째 줄에는 친구 관계의 수 F가 주어지며, 이 값은 100,000을 넘지 않는다.
 * 다음 F개의 줄에는 친구 관계가 생긴 순서대로 주어진다. 친구 관계는 두 사용자의 아이디로 이루어져 있으며, 알파벳 대문자 또는 소문자로만 이루어진 길이 20 이하의 문자열이다.
 *
 * 출력:
 * 친구 관계가 생길 때마다, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.
 */
public class Main {
    final static int Max = 200002;
    static int[] parents;
    static int[] cnt;
    static HashMap<String, Integer> map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(st.nextToken());

        for (int i = 0; i < test; i++) {
            parents = new int[Max];
            cnt = new int[Max];
            map = new HashMap();
            int index = 1;
            st = new StringTokenizer(br.readLine());
            int F = Integer.parseInt(st.nextToken());
            for(int j = 0; j < F; j++) {
                String[] friends = br.readLine().split(" ");
                for(int k = 0; k < 2; k++) {
                    if(!map.containsKey(friends[k])) {
                        map.put(friends[k], index);
                        parents[index] = index;
                        cnt[index] = 1;
                        index++;
                    }
                }
                union(map.get(friends[0]), map.get(friends[1]));
                sb.append(cnt[find(map.get(friends[0]))]).append("\n");
            }
        }
        System.out.print(sb.toString());
    }


    static int find(int x) {
        if (parents[x] == x) return parents[x];
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a == b) return; // 필수...!

        if(a > b){
            parents[b] = a;
            cnt[a] += cnt[b];
            cnt[b] = 0;
        }
        else{
            parents[a] = b;
            cnt[b] += cnt[a];
            cnt[a] = 0;
        }
    }

//    static boolean connected(int a, int b) { return find(a) == find(b); }
}

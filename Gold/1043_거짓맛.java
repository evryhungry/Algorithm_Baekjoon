import java.io.*;
import java.util.*;

/**
 *  지민이는 파티에 가서 이야기 하는 것을 좋아한다. 파티에 갈 때마다, 지민이는 지민이가 가장 좋아하는 이야기를 한다. 지민이는 그 이야기를 말할 때,
 *  있는 그대로 진실로 말하거나 엄청나게 과장해서 말한다. 당연히 과장해서 이야기하는 것이 훨씬 더 재미있기 때문에, 되도록이면 과장해서 이야기하려고 한다.
 *  하지만, 지민이는 거짓말쟁이로 알려지기는 싫어한다. 문제는 몇몇 사람들은 그 이야기의 진실을 안다는 것이다. 따라서 이런 사람들이 파티에 왔을 때는, 지민이는 진실을 이야기할 수 밖에 없다.
 *  당연히, 어떤 사람이 어떤 파티에서는 진실을 듣고, 또다른 파티에서는 과장된 이야기를 들었을 때도 지민이는 거짓말쟁이로 알려지게 된다. 지민이는 이런 일을 모두 피해야 한다.
 *
 * 사람의 수 N이 주어진다. 그리고 그 이야기의 진실을 아는 사람이 주어진다. 그리고 각 파티에 오는 사람들의 번호가 주어진다.
 * 지민이는 모든 파티에 참가해야 한다. 이때, 지민이가 거짓말쟁이로 알려지지 않으면서, 과장된 이야기를 할 수 있는 파티 개수의 최댓값을 구하는 프로그램을 작성하시오.
 *
 * N : 사람 수
 * M : 파티수
 * true_man = 아는 사람들
 */
public class Main {
    static int[] parents;
    static List<Integer> kList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 사람 수
        int m = Integer.parseInt(st.nextToken()); // 파티 수

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 수
        kList = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            kList.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer>[] parties = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int party_size = Integer.parseInt(st.nextToken());
            parties[i] = new ArrayList<>();

            int x = Integer.parseInt(st.nextToken());
            parties[i].add(x);
            for (int j = 1; j < party_size; j++) {
                int y = Integer.parseInt(st.nextToken());
                union(x, y);
                parties[i].add(y);
            }
        }

        int cnt = 0;
        for (int i = 0; i < m; i++) {
            boolean isLiePossible = true;
            for (int num : parties[i]) {
                if (kList.contains(find(num))) {
                    isLiePossible = false;
                    break;
                }
            }
            if (isLiePossible) cnt++;
        }
        System.out.println(cnt);
    }


    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]); // 경로 압축
    }

    static void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx != ry) {
            if (kList.contains(rx)) {
                parents[ry] = rx;
            } else {
                parents[rx] = ry;
            }
        }
    }
}

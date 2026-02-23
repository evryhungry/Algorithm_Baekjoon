import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7453

/**
 * 틀린 풀이
 * why?
 * hashMap의 메모리 용량 제한으로 시간초과. O(N^2)
 * HashMap의 상수가 너무 큼 + Long 오토박싱/GC/캐시 미스로 느려짐.
 */
public class Main {
    static int N;
    static Map<Long, Integer> map_left = new HashMap<>();
    static long[][] left;
    static long[][] right;
    static BufferedReader br ;

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        left = new long[N][2];
        right = new long[N][2];

        StringTokenizer st ;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            left[i][0] = Long.parseLong(st.nextToken());
            left[i][1] = Long.parseLong(st.nextToken());
            right[i][0] = Long.parseLong(st.nextToken());
            right[i][1] = Long.parseLong(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        long ans = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map_left.put(left[i][0] + left[j][1], map_left.getOrDefault(left[i][0] + left[j][1], 0) + 1);
            }
        }

        long temp = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp = (right[i][0] + right[j][1]) * -1;

                if (map_left.containsKey(temp)) {
                    ans += map_left.get(temp);
                }
            }
        }

        System.out.println(ans);
    }
}

/**
 * BOJ 7453: 합이 0인 네 정수 (4SUM)
 *
 * <p><b>문제 요약</b>
 * <br>길이 N인 배열 A, B, C, D가 주어질 때,
 * A[a] + B[b] + C[c] + D[d] = 0 을 만족하는 (a, b, c, d) 조합의 개수를 구한다.
 *
 * <p><b>핵심 아이디어 (Meet-in-the-Middle)</b>
 * <ul>
 *   <li>4개의 합을 직접 탐색하면 O(N^4)로 불가능하다.</li>
 *   <li>두 그룹으로 분할하여 (A+B)와 (C+D) 모든 합을 각각 N^2개 만든다.</li>
 *   <li>원하는 조건은 (A+B) + (C+D) = 0 이므로,
 *       (A+B) = -(C+D) 를 만족하는 쌍의 개수를 세면 된다.</li>
 * </ul>
 *
 * <p><b>구현 전략</b>
 * <ol>
 *   <li>AB 배열에 모든 A[i] + B[j]를 저장 (크기 N^2)</li>
 *   <li>CD 배열에 모든 C[i] + D[j]를 저장 (크기 N^2)</li>
 *   <li>AB, CD를 정렬</li>
 *   <li>투 포인터로 AB의 작은 값부터(l), CD의 큰 값부터(r) 이동하며,
 *       AB[l] + CD[r] == 0 인 구간을 만나면
 *       같은 값의 연속 구간 길이(cntAB, cntCD)를 세어 ans += cntAB * cntCD</li>
 * </ol>
 *
 * <p><b>왜 이 방식이 자바에서 특히 유리한가?</b>
 * <ul>
 *   <li>HashMap<Long, Integer> 방식은 N^2(최대 1,600만)번 박싱(Long 객체 생성),
 *       해시 탐색, 리사이즈, GC 부담이 커서 시간 초과가 자주 발생한다.</li>
 *   <li>반면 int[] 배열은 연속 메모리로 캐시 효율이 좋고,
 *       Arrays.sort는 최적화된 정렬을 사용하여 매우 빠르다.</li>
 *   <li>투 포인터는 정렬 후 O(N^2) 배열을 한 번만 선형으로 훑는다.</li>
 * </ul>
 *
 * <p><b>정확성 포인트</b>
 * <ul>
 *   <li>중복 값이 있는 경우, 같은 값의 연속 구간을 묶어서 cntAB * cntCD만큼
 *       조합 수를 한 번에 더하므로 누락/중복 없이 정확히 카운팅한다.</li>
 *   <li>정답은 최대 N^4까지 커질 수 있으므로 ans는 long을 사용한다.</li>
 * </ul>
 *
 * <p><b>시간 복잡도</b>
 * <ul>
 *   <li>AB, CD 생성: O(N^2)</li>
 *   <li>정렬: O(N^2 log(N^2)) = O(N^2 log N)</li>
 *   <li>투 포인터 탐색: O(N^2)</li>
 * </ul>
 * <p>따라서 전체 시간은 <b>O(N^2 log N)</b> 이며,
 * N=4000에서도 통과 가능한 표준 해법이다.
 *
 * <p><b>공간 복잡도</b>
 * <ul>
 *   <li>AB, CD 배열 2개: O(N^2)</li>
 * </ul>
 *
 * <p><b>주의</b>
 * <ul>
 *   <li>size = N*N은 N=4000에서 16,000,000으로 int 범위 내이지만,
 *       ans는 long이어야 한다.</li>
 * </ul>
 */
public class Main {
    static int N;
    static int size;
    static int[] AB ;
    static int[] CD ;

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        size = N * N;
        AB = new int[size];
        CD = new int[size];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx] = C[i] + D[j];
                idx++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        init();

        Arrays.sort(AB);
        Arrays.sort(CD);

        int l = 0;
        int r = size - 1;
        long ans = 0;

        while (l < size && r >= 0) {
            long sum = (long) AB[l] + (long) CD[r];

            if (sum == 0) {
                int abVal = AB[l];
                int cdVal = CD[r];

                long cntAB = 0;
                while (l < size && AB[l] == abVal) {
                    cntAB++;
                    l++;
                }

                long cntCD = 0;
                while (r >= 0 && CD[r] == cdVal) {
                    cntCD++;
                    r--;
                }

                ans += cntAB * cntCD;
            } else if (sum < 0) {
                l++;
            } else {
                r--;
            }
        }

        System.out.println(ans);
    }
}
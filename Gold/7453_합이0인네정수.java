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

/**
 * 왜 이(HashMap 기반) 풀이가 BOJ 7453에서 시간초과가 나는가?
 *
 * <p><b>겉으로는 O(N^2)</b>로 보이지만, Java에서 {@link HashMap} + {@link Long} 키를
 * 1,600만(=4000^2) 수준으로 다루면 상수 비용이 너무 커져 시간 제한을 초과하기 쉽다.</p>
 *
 * 1) Long 오토박싱(객체 생성) 비용
 * <ul>
 *   <li>맵 타입이 {@code Map<Long, Integer>} 이므로, {@code long} 합을 넣을 때마다
 *       {@code Long.valueOf(sum)} 형태의 <b>박싱</b>이 발생한다.</li>
 *   <li>N=4000이면 AB 합을 만드는 루프만 {@code N^2 = 16,000,000}번 실행되며,
 *       이 과정에서 매우 많은 Long 객체/참조가 관여한다.</li>
 *   <li>결과적으로 <b>메모리 사용량 증가 + GC(가비지 컬렉션) 부담</b>이 커진다.</li>
 * </ul>
 *
 * 2) HashMap 접근의 높은 상수 시간 (해시 계산 + 버킷 탐색)
 * <ul>
 *   <li>각 put/get에는 해시 계산, 버킷 위치 탐색, 충돌 처리(체이닝/트리화) 등의 비용이 따른다.</li>
 *   <li>배열처럼 연속 메모리를 순회하는 작업이 아니라, 랜덤 접근이 많아
 *       <b>CPU 캐시 효율이 낮아</b> 실제 실행 시간이 크게 증가한다.</li>
 * </ul>
 *
 * 3) 리사이즈(rehash) 비용
 * <ul>
 *   <li>AB 합의 “서로 다른 값” 개수가 많아지면 맵 엔트리 수도 커지고,
 *       로드 팩터를 넘으면 내부 배열이 커지며 <b>리사이즈 + 재해싱</b>이 발생한다.</li>
 *   <li>리사이즈는 단발성 비용이 아니라 누적되며, 큰 입력에서 체감 시간이 크게 늘어난다.</li>
 * </ul>
 *
 * 4) containsKey + get 이중 조회로 동일 탐색 2번
 * <ul>
 *   <li>두 번째 루프에서 {@code containsKey(temp)} 후 {@code get(temp)}를 호출하므로,
 *       같은 키에 대해 해시 계산/탐색을 <b>2번</b> 수행한다.</li>
 *   <li>총 조회 횟수도 {@code N^2 = 16,000,000}번이므로, 이중 탐색은 시간을 더 악화시킨다.</li>
 *   <li>대안: {@code Integer cnt = map.get(temp); if (cnt != null) ans += cnt;} (탐색 1회)</li>
 * </ul>
 *
 * 정리
 * <ul>
 *   <li>이 풀이는 이론상 {@code O(N^2)}지만, Java에서 HashMap + Long을 대량으로 다루는 상수 비용이
 *       너무 커서 실제로는 시간 제한을 넘기기 쉽다.</li>
 *   <li>따라서 BOJ 7453에서는 보통
 *       <b>AB, CD를 int[] 배열에 저장 → 정렬 → 투 포인터/이분탐색</b> 방식이
 *       더 빠르고 안정적으로 통과한다.</li>
 * </ul>
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
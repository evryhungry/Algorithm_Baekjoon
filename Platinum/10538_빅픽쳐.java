import java.io.*;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10538

/**
 * 큰 격자(map) 안에서 작은 격자(picture)가 모든 칸이 정확히 일치하는 위치의 개수를 세는 2D 패턴 매칭 문제.
 * 첫 풀이(브루트포스): 모든 (i,j) 위치마다 h*w를 직접 비교 → O((H-h+1)(W-w+1)*h*w)로 시간초과.
 * 개선: 2D 롤링 해시(Rabin–Karp)로 각 블록의 해시를 전처리(O(HW))하고,
 *       이후 각 위치는 해시값(더블 해시) O(1) 비교로 판별한다.
 */
public class Main {
    static int h_picture, w_picture, h_map, w_map;
    static char[][] pictures;
    static char[][] map;
    static final long MOD1 = 1_000_000_007L;
    static final long MOD2 = 1_000_000_008L;
    static final long BASE_COL = 1_000_000_002L;
    static final long BASE_ROW = 1_000_000_003L;
    static long[] powCol1, powCol2, powRow1, powRow2;
    static long[][] block1, block2;
    static long pat1, pat2;

    private static void buildPowers() {
        int maxW = Math.max(w_map, w_picture);
        int maxH = Math.max(h_map, h_picture);

        powCol1 = new long[maxW + 1];
        powCol2 = new long[maxW + 1];
        powRow1 = new long[maxH + 1];
        powRow2 = new long[maxH + 1];

        powCol1[0] = powCol2[0] = 1;
        for (int i = 1; i <= maxW; i++) {
            powCol1[i] = (powCol1[i - 1] * BASE_COL) % MOD1;
            powCol2[i] = (powCol2[i - 1] * BASE_COL) % MOD2;
        }

        powRow1[0] = powRow2[0] = 1;
        for (int i = 1; i <= maxH; i++) {
            powRow1[i] = (powRow1[i - 1] * BASE_ROW) % MOD1;
            powRow2[i] = (powRow2[i - 1] * BASE_ROW) % MOD2;
        }
    }

    private static void buildPatternHash() {
        long[] rowHash1 = new long[h_picture];
        long[] rowHash2 = new long[h_picture];

        for (int i = 0; i < h_picture; i++) {
            long h1 = 0, h2 = 0;
            for (int j = 0; j < w_picture; j++) {
                int v = pictures[i][j];
                h1 = (h1 * BASE_COL + v) % MOD1;
                h2 = (h2 * BASE_COL + v) % MOD2;
            }
            rowHash1[i] = h1;
            rowHash2[i] = h2;
        }

        long v1 = 0, v2 = 0;
        for (int i = 0; i < h_picture; i++) {
            v1 = (v1 * BASE_ROW + rowHash1[i]) % MOD1;
            v2 = (v2 * BASE_ROW + rowHash2[i]) % MOD2;
        }
        pat1 = v1;
        pat2 = v2;
    }

    private static void buildMapBlockHashes() {
        int outH = h_map - h_picture + 1;
        int outW = w_map - w_picture + 1;

        block1 = new long[outH][outW];
        block2 = new long[outH][outW];

        long[][] rowWin1 = new long[h_map][outW];
        long[][] rowWin2 = new long[h_map][outW];

        for (int i = 0; i < h_map; i++) {
            long[] pref1 = new long[w_map + 1];
            long[] pref2 = new long[w_map + 1];

            for (int j = 0; j < w_map; j++) {
                int v = map[i][j];
                pref1[j + 1] = (pref1[j] * BASE_COL + v) % MOD1;
                pref2[j + 1] = (pref2[j] * BASE_COL + v) % MOD2;
            }

            for (int j = 0; j < outW; j++) {
                long x1 = (pref1[j + w_picture] - (pref1[j] * powCol1[w_picture]) % MOD1 + MOD1) % MOD1;
                long x2 = (pref2[j + w_picture] - (pref2[j] * powCol2[w_picture]) % MOD2 + MOD2) % MOD2;
                rowWin1[i][j] = x1;
                rowWin2[i][j] = x2;
            }
        }

        for (int j = 0; j < outW; j++) {
            long[] prefV1 = new long[h_map + 1];
            long[] prefV2 = new long[h_map + 1];

            for (int i = 0; i < h_map; i++) {
                prefV1[i + 1] = (prefV1[i] * BASE_ROW + rowWin1[i][j]) % MOD1;
                prefV2[i + 1] = (prefV2[i] * BASE_ROW + rowWin2[i][j]) % MOD2;
            }

            for (int i = 0; i < outH; i++) {
                long v1 = (prefV1[i + h_picture] - (prefV1[i] * powRow1[h_picture]) % MOD1 + MOD1) % MOD1;
                long v2 = (prefV2[i + h_picture] - (prefV2[i] * powRow2[h_picture]) % MOD2 + MOD2) % MOD2;
                block1[i][j] = v1;
                block2[i][j] = v2;
            }
        }
    }

    private static boolean isPossible(int h, int w) {
        return block1[h][w] == pat1 && block2[h][w] == pat2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h_picture = Integer.parseInt(st.nextToken());
        w_picture = Integer.parseInt(st.nextToken());
        h_map = Integer.parseInt(st.nextToken());
        w_map = Integer.parseInt(st.nextToken());

        pictures = new char[h_picture][];
        map = new char[h_map][];

        for (int i = 0; i < h_picture; i++) pictures[i] = br.readLine().trim().toCharArray();
        for (int i = 0; i < h_map; i++) map[i] = br.readLine().trim().toCharArray();

        buildPowers();
        buildPatternHash();
        buildMapBlockHashes();

        int cnt = 0;
        for (int i = 0; i <= h_map - h_picture; i++) {
            for (int j = 0; j <= w_map - w_picture; j++) {
                if (isPossible(i, j)) cnt++;
            }
        }

        System.out.println(cnt);
    }
}
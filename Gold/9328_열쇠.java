import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static String[][] map;
    static int x;
    static int y;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static List<String> keys;
    static BufferedReader br ;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        map = new String[x][y];
        visited = new boolean[x][y];
        keys = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            map[i] = br.readLine().split("");
        }

        String line = br.readLine();
        if (line.equals("0")) return;
        else {
            for (char ch : line.toCharArray()) {
                keys.add(String.valueOf(Character.toUpperCase(ch)));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            init();
            sb.append(bfs()).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static int bfs() {
        List<int[]>[] wait = new ArrayList[26];
        for (int i = 0; i < 26; i++) wait[i] = new ArrayList<>();

        boolean[] hasKey = new boolean[26];
        for (String k : keys) {
            hasKey[k.charAt(0) - 'A'] = true;
        }

        ArrayList<int[]> q = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            addStart(i, 0, q, hasKey, wait);
            addStart(i, y - 1, q, hasKey, wait);
        }
        for (int j = 0; j < y; j++) {
            addStart(0, j, q, hasKey, wait);
            addStart(x - 1, j, q, hasKey, wait);
        }

        int head = 0;
        int docs = 0;

        while (head < q.size()) {
            int[] cur = q.get(head++);
            int cx = cur[0], cy = cur[1];

            String cell = map[cx][cy];

            if (cell.equals("$")) {
                docs++;
                map[cx][cy] = ".";
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];
                if (nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                if (visited[nx][ny]) continue;

                String next = map[nx][ny];
                if (next.equals("*")) continue;

                if (isDoor(next)) {
                    int idx = next.charAt(0) - 'A';
                    if (!hasKey[idx]) {
                        visited[nx][ny] = true;
                        wait[idx].add(new int[]{nx, ny});
                        continue;
                    }
                }

                if (isKey(next)) {
                    int idx = Character.toUpperCase(next.charAt(0)) - 'A';
                    if (!hasKey[idx]) {
                        hasKey[idx] = true;

                        for (int[] pos : wait[idx]) {
                            q.add(pos);
                        }
                        wait[idx].clear();
                    }
                    map[nx][ny] = ".";
                }

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        return docs;
    }

    private static void addStart(int sx, int sy, ArrayList<int[]> q, boolean[] hasKey, List<int[]>[] wait) {
        if (sx < 0 || sy < 0 || sx >= x || sy >= y) return;
        if (visited[sx][sy]) return;

        String cell = map[sx][sy];
        if (cell.equals("*")) return;

        if (isDoor(cell)) {
            int idx = cell.charAt(0) - 'A';
            if (!hasKey[idx]) {
                visited[sx][sy] = true;
                wait[idx].add(new int[]{sx, sy});
                return;
            }
        }

        if (isKey(cell)) {
            int idx = Character.toUpperCase(cell.charAt(0)) - 'A';
            if (!hasKey[idx]) {
                hasKey[idx] = true;
            }
            map[sx][sy] = ".";
        }

        visited[sx][sy] = true;
        q.add(new int[]{sx, sy});
    }

    private static boolean isDoor(String s) {
        if (s.length() != 1) return false;
        char c = s.charAt(0);
        return c >= 'A' && c <= 'Z';
    }

    private static boolean isKey(String s) {
        if (s.length() != 1) return false;
        char c = s.charAt(0);
        return c >= 'a' && c <= 'z';
    }

}
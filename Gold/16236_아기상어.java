// https://www.acmicpc.net/problem/16236
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] space;
    static PriorityQueue<Shark> pq = new PriorityQueue<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static class Shark implements Comparable<Shark> {
        int x, y, distance;

        public Shark(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Shark o) {
            if (this.distance != o.distance) return Integer.compare(this.distance, o.distance);
            else {
                if (this.x == o.x) return Integer.compare(this.y, o.y);
                else return Integer.compare(this.x, o.x);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        space = new int[N][N];
        StringTokenizer st;
        Queue<Shark> queue = new LinkedList();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if (space[i][j] == 9) {
                    space[i][j] = 0;
                    queue.offer(new Shark(i, j, 0));
                }
            }
        }

        bfs(queue, 2);
        int move = 0, cnt = 0, sharkSize = 2;
        while (!pq.isEmpty()) {
            Shark now = pq.poll();
            space[now.x][now.y] = 0;
            if (++cnt == sharkSize) {
                cnt = 0;
                sharkSize++;
            }
            move += now.distance;
            queue = new LinkedList();
            queue.offer(new Shark(now.x, now.y, 0));
            bfs(queue, sharkSize);
        }
        System.out.println(move);

    }

    private static void bfs(Queue<Shark> queue, int sharkSize) {
        pq = new PriorityQueue<>();
        boolean visited[][] = new boolean[N][N];
        while (!queue.isEmpty()) {
            Shark now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || space[nx][ny] > sharkSize || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                queue.offer(new Shark(nx, ny, now.distance + 1));
                if (space[nx][ny] != 0 && space[nx][ny] < sharkSize) {
                    pq.offer(new Shark(nx, ny, now.distance + 1));
                }

            }
        }
    }
}
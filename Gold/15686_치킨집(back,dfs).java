import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/15686
public class Main {
    static int n, m;
    static int[][] map;
    static boolean[] open;
    static ArrayList<Location> chickens;
    static ArrayList<Location> houses;
    static int answer = Integer.MAX_VALUE;

    static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        chickens = new ArrayList<>();
        houses = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    houses.add(new Location(i, j));
                } else if (map[i][j] == 2) {
                    chickens.add(new Location(i, j));
                }
            }
        }

        open = new boolean[chickens.size()];

        dfs(0, 0);
        System.out.println(answer);
    }


    static void dfs(int start, int cnt){
        if (cnt == m){
            int rest = 0;

            for (int i = 0; i < houses.size(); i++){
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < chickens.size(); j++){
                    if(open[j]) {
                        int dist = Math.abs(houses.get(i).x - chickens.get(j).x)
                                + Math.abs(houses.get(i).y - chickens.get(j).y);

                        temp = Math.min(dist, temp);
                    }
                }
                rest += temp;
            }
            answer = Math.min(answer, rest);
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            open[i] = true;
            dfs(i + 1, cnt + 1);
            open[i] = false;
        }
    }
}
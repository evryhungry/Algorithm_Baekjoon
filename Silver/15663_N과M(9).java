import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/15663
public class Main {
    static int[] arr;
    static int[] out;
    static boolean[] visited;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];
        out = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        back_tracking(0);
    }

    // back-tracking
    public static void back_tracking (int cur){
        if(cur == M){
            for(int i = 0; i < M; i++)
                System.out.print(out[i]+" ");
            System.out.println();
        }
        else{
            int before = 0;
            for(int i = 0; i < N; i++){
                if(visited[i]){
                    continue;
                }

                if(before != arr[i]){
                    visited[i] = true;
                    out[cur] = arr[i];
                    before = arr[i];
                    back_tracking(cur+1);
                    visited[i] = false;
                }
            }
        }
    }
}

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/27172

/**
 * 첫 풀이: 이중 for문  1000000 * 1000000 -> 시간초과
 *
 * 2번쨰 풀이:
 *  최대 크기 숫자를 알고나서 -> 해당 숫자의 배수를 찾기 위함.
 *  해당 배수에 index 정보를 담고.
 *  해당 배수 정보의 index 크기만크 ㅁ추가하는 방식.
 */
public class Main {
    static int N;
    static int[] userScores;
    static int[] userNumber;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        userScores = new int[N];
        userNumber = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < N; i++) {
            userNumber[i] = Integer.parseInt(st.nextToken());
            if (userNumber[i] > max) max = userNumber[i];
        }

        int[] pos = new int[max + 1];
        Arrays.fill(pos, -1);

        for (int i = 0; i < N; i++) {
            pos[userNumber[i]] = i;
        }

        for (int i = 0; i < N; i++) {
            int v = userNumber[i];
            for (int k = v * 2; k <= max; k += v) {
                int j = pos[k];
                if (j == -1) continue;
                userScores[i]++;
                userScores[j]--;
            }
        }

        for (int i = 0; i < N; i++) bw.write(userScores[i] + " ");
        bw.newLine();
        bw.flush();
        br.close();
    }
}
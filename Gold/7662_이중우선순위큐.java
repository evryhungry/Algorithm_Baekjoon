import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// https://www.acmicpc.net/problem/7662
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) operate(Integer.parseInt(br.readLine()), sb);

        bw.write(sb.toString());
        bw.flush();
    }

    public static void operate(int k, StringBuilder sb) throws IOException {
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        HashMap<Integer, Integer> mapQ = new HashMap<>();
        int qSize = 0;

        for (int i = 0 ; i < k; i++) {
            String[] s = br.readLine().split(" ");
            char mas = s[0].charAt(0);
            int val = Integer.valueOf(s[1]);

            if(mas == 'I'){
                minQ.add(val);
                maxQ.add(val);
                mapQ.put(val, mapQ.getOrDefault(val, 0) + 1);
                qSize++;
            } else if(mas == 'D'){
                if (qSize == 0) continue;

                if(val == 1) {
                    int maxValue = maxQ.poll();
                    while (mapQ.get(maxValue) == 0) maxValue = maxQ.poll();

                    mapQ.replace(maxValue, mapQ.getOrDefault(maxValue, 0) - 1);
                }
                else if (val == -1) {
                    int minValue = minQ.poll();
                    while (mapQ.get(minValue) == 0) minValue = minQ.poll();

                    mapQ.replace(minValue, mapQ.getOrDefault(minValue, 0) - 1);
                }
                qSize--;
            }
        }

        List<Integer> found0key = mapQ.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), 0))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if(qSize == 0) {
            sb.append("EMPTY\n");
            return;
        }
        else {
            int max = maxQ.poll();
            int min = minQ.poll();
            while(found0key.contains(max)) max = maxQ.poll();
            while(found0key.contains(min)) min = minQ.poll();

            sb.append(max).append(" ");
            sb.append(min).append("\n");
        }
    }
}


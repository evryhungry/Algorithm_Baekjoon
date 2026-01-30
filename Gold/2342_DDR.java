//import java.io.*;
//import java.util.StringTokenizer;
//
//// https://www.acmicpc.net/problem/2342
//public class Main {
//    private static class Node {
//        int left;
//        int right;
//        int weight;
//
//        Node(int left, int right, int weight) {
//            this.left = left;
//            this.right = right;
//            this.weight = weight;
//        }
//
//        int moveCost(int from, int to) {
//            if (from == to) return 1;
//            if (from == 0) return 2;
//            if (Math.abs(from - to) == 2) return 4;
//            return 3;
//        }
//
//        Node moveLeftNode(int pos) {
//            return new Node(pos, right, weight + moveCost(left, pos));
//        }
//
//        Node moveRightNode(int pos){
//            return new Node(left, pos, weight + moveCost(right, pos));
//        }
//    }
//    // 틀림 한줄롼 풀음
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        Node start = new Node(0, 0, 0);
//        Node end = new Node(0, 0, 0);
//
//        while (st.hasMoreTokens()) {
//            int pos = Integer.parseInt(st.nextToken());
//            if (pos == 0) break;
//
//            Node leftNode = start.moveLeftNode(pos);
//            Node rightNode = start.moveRightNode(pos);
//
//            start = leftNode.weight < rightNode.weight ? leftNode : rightNode;
//        }
//
//        bw.write(String.valueOf(start.weight));
//        bw.flush();
//        bw.close();
//        br.close();
//    }
//}

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2342
public class Main {
    private static class Node {
        int left;
        int right;
        int weight;

        Node(int left, int right, int weight) {
            this.left = left;
            this.right = right;
            this.weight = weight;
        }

        int moveCost(int from, int to) {
            if (from == to) return 1;
            if (from == 0) return 2;
            if (Math.abs(from - to) == 2) return 4;
            return 3;
        }

        Node moveLeftNode(int pos) {
            return new Node(pos, right, weight + moveCost(left, pos));
        }

        Node moveRightNode(int pos){
            return new Node(left, pos, weight + moveCost(right, pos));
        }
    }

    static int key(int l, int r) { return l * 10 + r; }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Map<Integer, Integer> states = new HashMap<>();
        states.put(key(0, 0), 0);

        while (st.hasMoreTokens()) {
            int pos = Integer.parseInt(st.nextToken());
            if (pos == 0) break;

            Map<Integer, Integer> next = new HashMap<>();

            for (Map.Entry<Integer, Integer> e : states.entrySet()) {
                int k = e.getKey();
                int w = e.getValue();
                int l = k / 10;
                int r = k % 10;

                Node cur = new Node(l, r, w);

                Node leftNode = cur.moveLeftNode(pos);
                int k1 = key(leftNode.left, leftNode.right);
                next.put(k1, Math.min(next.getOrDefault(k1, Integer.MAX_VALUE), leftNode.weight));

                Node rightNode = cur.moveRightNode(pos);
                int k2 = key(rightNode.left, rightNode.right);
                next.put(k2, Math.min(next.getOrDefault(k2, Integer.MAX_VALUE), rightNode.weight));
            }

            states = next;
        }

        int ans = Integer.MAX_VALUE;
        for (int w : states.values()) ans = Math.min(ans, w);
        System.out.println(ans);
    }
}


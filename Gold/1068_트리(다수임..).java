import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1068
class Node {
    int item;
    // 바보짓 1. 이진트리만 생각한 점.
    List<Node> children;

    public Node(int item) {
        this.item = item;
        this.children = new ArrayList<>();
    }
}

class Tree {
    Node root;
    Map<Integer, Node> nodeMap = new HashMap<>();

    // 바보짓 2. 부모노드가 항상 먼저 나온다는 생각을 한점..
    public void createTree(int[] parents) {
        for (int i = 0; i < parents.length; i++) {
            nodeMap.put(i, new Node(i));
        }

        for (int i = 0; i < parents.length; i++) {
            int parent = parents[i];
            if (parent == -1) {
                root = nodeMap.get(i);
            } else {
                nodeMap.get(parent).children.add(nodeMap.get(i));
            }
        }
    }

    public void deleteSubTree(int value) {
        if (root == null) return;
        if (root.item == value) {
            root = null;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            current.children.removeIf(child -> child.item == value);
            queue.addAll(current.children);
        }
    }

    public int countLeaves(Node node) {
        if (node == null) return 0;
        if (node.children.isEmpty()) return 1;
        int count = 0;
        for (Node child : node.children) {
            count += countLeaves(child);
        }
        return count;
    }
}

public class Main {
    static int n, m;
    static Tree tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int[] parents = new int[n];
        tree = new Tree();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            parents[i] = Integer.parseInt(st.nextToken());
        }
        tree.createTree(parents);

        m = Integer.parseInt(br.readLine());
        tree.deleteSubTree(m);

        System.out.println(tree.root == null ? 0 : tree.countLeaves(tree.root));
    }
}

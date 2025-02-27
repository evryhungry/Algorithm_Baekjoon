import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1991
class Node {
    char item;
    Node left;
    Node right;

    public Node(char item) {
        this.item = item;
    }
}

class Tree {
    Node root;

    public void createNode(char data, char leftData, char rightData){
        if(root == null){
            this.root = new Node(data);
            this.root.left = leftData == '.' ? null : new Node(leftData);
            this.root.right = rightData == '.' ? null : new Node(rightData);
        } else {
            searchNode(root, data, leftData, rightData);
        }

    }

    public void searchNode(Node node, char data, char leftData, char rightData){
        if (node == null) return;
        else if (node.item == data) {
            node.left = leftData == '.' ? null : new Node(leftData);
            node.right = rightData == '.' ? null : new Node(rightData);
        } else {
            searchNode(node.left, data, leftData, rightData);
            searchNode(node.right, data, leftData, rightData);
        }
    }

    public void preorder(Node node){
        if (node == null) return;
        System.out.print(node.item);
        preorder(node.left);
        preorder(node.right);
    }

    public void inorder(Node node){
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.item);
        inorder(node.right);
    }

    public void postorder(Node node){
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.item);
    }
}

public class Main {
    static Tree tree;
    static int n;
    static char data;
    static char leftData;
    static char rightData;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        tree = new Tree();
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            data = st.nextToken().charAt(0);
            leftData = st.nextToken().charAt(0);
            rightData = st.nextToken().charAt(0);

            tree.createNode(data, leftData, rightData);
        }

        tree.preorder(tree.root);
        System.out.println();
        tree.inorder(tree.root);
        System.out.println();
        tree.postorder(tree.root);
    }

}
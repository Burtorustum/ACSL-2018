import java.util.Arrays;
import java.util.Scanner;

/*
abracadabracabob                                - 15
American Computer Science League                - 9
Python and Java are programming languages       - 23
Python and Java and java and python             - 18
the quick brown fox jumped over the lazy river  - 9
*/

public class ACSL4_Aburustum {

    private final static char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F','G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        BinarySearchTree tree = new BinarySearchTree();

        while (true) {
            System.out.println("Please input a line of input data. ");
            tree.buildTree(parseInput(reader.nextLine()));
            System.out.println("Result: " + tree.getSingleSums() + "\n");
        }
    }

    private static int[] parseInput(String inp) {
        char[] inpArr = inp.toUpperCase().toCharArray();
        inp = "";

        for (char c : inpArr) {
            for (char t : ALPHABET) {
                if (c == t) {
                    inp += c;
                }
            }
        }

        inpArr = inp.toCharArray();
        int[] inpInts = new int[inpArr.length];

        for (int i = 0; i < inpArr.length; i++) {
            inpInts[i] = Arrays.binarySearch(ALPHABET, inpArr[i]);
        }

        return inpInts;
    }
}

class BinarySearchTree {

    private class Node {
        final int key;
        int counter;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.counter = 1;
            this.left = this.right = null;
        }

        int getNumChildren() {
            return (this.left == null ? 0 : 1) + (this.right == null ? 0 : 1);
        }
    }

    private Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int key) {
        this.root = insertRecursive(this.root, key);
    }

    private Node insertRecursive(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key == root.key) {
            root.counter++;
        } else if (key < root.key) {
            root.left = insertRecursive(root.left, key);
        } else {
            root.right = insertRecursive(root.right, key);
        }

        return root;
    }

    int getSingleSums() {
        return getSingleSumsRec(this.root);
    }

    private int getSingleSumsRec(Node root) {
        if (root != null) {
            return getSingleSumsRec(root.left) + getSingleSumsRec(root.right) + (root.getNumChildren() == 1 ? root.counter : 0);
        }
        return 0;
    }

    void buildTree(int[] arr) {
        this.root = null;
        for (int i : arr) {
            this.insert(i);
        }
    }

}
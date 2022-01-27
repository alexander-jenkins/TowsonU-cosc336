// COSC 336.001 - Assignment 7
// Alexander Jenkins, Darwin Martinez-Delcid, Melvin Noguera, Michael Fazio

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Main {
    public static void main(String[] args) throws IOException {
        // create the BSTs using the provided data
        BinarySearchTree tree0 = fillTree(readInputFile("testData1"));
        BinarySearchTree tree1 = fillTree(readInputFile("input-7.1"));
        BinarySearchTree tree2 = fillTree(readInputFile("input-7.2"));
        BinarySearchTree tree3 = fillTree(readInputFile("testData2"));

        // set the sizes of nodes
        tree0.setSize(tree0.root);
        tree1.setSize(tree1.root);
        tree2.setSize(tree2.root);
        tree3.setSize(tree3.root);

        // print inorder traversal of the BST with their respective keys and sizes
        // tree1.printTree();
        // tree2.printTree();
        //tree0.printTree();
        //tree3.printTree();

        // find the k-th smallest value
        int k1 = tree0.select(tree0.root, (tree0.getSize(tree0.root)/ 2) + 1);
        int k2 = tree1.select(tree1.root, (tree1.getSize(tree1.root)/ 2) + 1);
        int k3 = tree2.select(tree2.root, (tree2.getSize(tree2.root)/ 2) + 1);
        int k4 = tree3.select(tree3.root, (tree3.getSize(tree3.root)/ 2) + 1);

        // print each k-th smallest value
        System.out.printf("The k-th smallest value for test data one is: %d\n", k1);
        System.out.printf("The k-th smallest value for input-7.1 is: %d\n", k2);
        System.out.printf("The k-th smallest value for input-7-2 is: %d\n", k3);
        System.out.printf("The k-th smallest value for test data two is: %d\n", k4);
    }

    // function to read in data from files
    private static int[] readInputFile(String fileName) throws IOException {
        // Open the file and create the scanner
        File rawData = new File(fileName);
        Scanner s = new Scanner(rawData);

        // Get n, the amount of numbers
        int n = s.nextInt();

        // Fill array with data from the file
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++)
            numbers[i] = s.nextInt();

        return numbers;
    }

    // function to fill tree from array
    private static BinarySearchTree fillTree(int[] numbers) {
        BinarySearchTree tree = new BinarySearchTree();
        for (int num : numbers)
            tree.insert(num);
        return tree;
    }
}

class BinarySearchTree {
    // Class containing left and right child of current node and key value
    private static class Node {
        int key, size;
        Node left, right;

        private Node(int item) {
            key = item;
            size = 1;
            left = right = null;
        }

    }

    // Root of BST
    Node root;

    // Constructor
    public BinarySearchTree() {
        root = null;
    }

    // This method mainly calls insertRec()
    public void insert(int key) {
        root = insertRec(root, key);
    }

    // A recursive function to insert a new key in BST
    Node insertRec(Node root, int key) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Otherwise, recur down the tree
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key >= root.key)
            root.right = insertRec(root.right, key);

        // return the (unchanged) node pointer
        return root;
    }

    public void setSize(Node root) {
        if (root != null) {
            root.size = getSize(root);
            if (root.left != null)setSize(root.left);
            if (root.right != null)setSize(root.right);
        }
    }

    public int getSize(Node root) {
        if (root == null) return 0;
        else return getSize(root.left) + getSize(root.right) + 1;
    }


    // function to print the binary tree
    public void printTree() {
        printTree(root);
        System.out.println();
    }

    // overloaded printTree function
    private void printTree(Node root) {
        if (root != null) {
            printTree(root.left);
            printTree(root.right);
            System.out.printf("(%d, %d) ", root.key, root.size);
        }
    }

    // find the k-th smallest node and return its value or -1 depending on if null
    public int select(Node root, int k) {
        // count number of nodes processed
        AtomicInteger counter = new AtomicInteger(0);

        // find the kth smallest
        Node smallest = select(root, k, counter);
        if (smallest == null) return -1;
        else return smallest.key;
    }

    private Node select(Node root, int k, AtomicInteger counter) {
        // base case
        if (root == null) return null;

        // search through left subtree
        Node left = select(root.left, k, counter);

        // if k'th smallest node is found
        if (left != null) return left;

        // if the root is k'th smallest node
        if (counter.incrementAndGet() == k) return root;

        // search through right subtree
        return select(root.right, k, counter);
    }

}

/*
    COSC 336 - Assignment 10
    Group:
        - Alexander Jenkins
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // generate the adjacency matrices for the two graphs
        int[][] matrix1 = parseFile("input-10-1.txt");
        int[][] matrix2 = parseFile("input-10-2.txt");

        // debugging -- print the matrices
        System.out.println(Arrays.deepToString(matrix1));
        System.out.println(Arrays.deepToString(matrix2));
    }

    // reuse graph code from assign9
    // returns an adjacency matrix from an array of values
    private static int[][] parseFile(String fname) throws IOException {
        // create the scanner and open the file from fname
        File file = new File(fname);
        Scanner data = new Scanner(file);

        // create the matrix of node data; n = num nodes
        int n = data.nextInt();
        int[][] nodeData = new int[n][n];

        // fill the matrix with node data
        // loop each row
        for (int i = 0; i < n; i++) {
            // loop each column
            for (int j = 0; j < n; j++) {
                // insert the connection into the matrix
                nodeData[i][j] = data.nextInt();
            }
        }

        // return the node data
        data.close();
        return nodeData;
    }

}
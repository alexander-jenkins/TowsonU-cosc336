/*
    COSC 336 - Assignment 9
    Group:
        - Alexander Jenkins
        - Darwin Martinez Delcid
        - Melvin Noguera Monroy

    Note: Michael Fazio usually is included in our group. However, this time he did not participate at all. Please see above for the updated members.
*/


import java.util.*;
import java.io.*;

class TaskOne {
    public static void main(String args[]) throws IOException {
        // parse the original data
        int[][] data1 = parseFile("input-9.1.txt");
        int[][] data2 = parseFile("input-9.2.txt");
        
        // create the squared matrices
        int[][] list1 = squareAdjacencyList(data1);
        int[][] list2 = squareAdjacencyList(data2);

        // create the graphs
        Adj_List_Graph graph1 = createGraph(list1);
        Adj_List_Graph graph2 = createGraph(list2);

        // print the adjacency lists
        graph1.printGraph();
        graph2.printGraph();
    }

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

    // create an adjacency list from the array of connections
    private static Adj_List_Graph createGraph(int[][] connections) {
        // create an empty graph from the number of nodes
        Adj_List_Graph graph = new Adj_List_Graph(connections.length);

        // add the edges to the graph
        // loop through the matrix and add edges to the graph when needed
        for (int row = 0; row < connections.length; row++) {
            for (int column = 0; column < connections.length; column++) {
                // is there a connection between the node at row and column?
                if (connections[row][column] == 1) {
                    graph.addEdge(row, column);
                }
            }
        }
        return graph;
    }

    // square the adjacency list
    private static int[][] squareAdjacencyList (int[][] adjacencyList) {
        // to find G^2, first square the ajdacency list A and then add to A
        int[][] squaredAdjacencyList = new int[adjacencyList.length][adjacencyList.length];

        // square the original adjacency matrix
        // loop each row
        for (int i = 0; i < adjacencyList.length; i++) {
            // loop each column
            for (int j = 0; j < adjacencyList.length; j++) {
                // loop columns again
                for (int k = 0; k < adjacencyList.length; k++) {
                    // multiply the matrices
                    squaredAdjacencyList[i][j] += adjacencyList[i][k] * adjacencyList[k][j];
                }
            }
        }

        // add the two matrices together
        // loop each row
        for (int i = 0; i < squaredAdjacencyList.length; i++) {
            // loop each column
            for (int j = 0; j < squaredAdjacencyList.length; j++) {
                // add the original list to the squared version
                squaredAdjacencyList[i][j] += adjacencyList[i][j];
            }
        }
        return squaredAdjacencyList;
    }
}

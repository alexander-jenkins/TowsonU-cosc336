/*
    COSC 336 - Assignment 9
    Group:
        - Alexander Jenkins
        - Darwin Martinez Delcid
        - Melvin Noguera Monroy

    Note: Michael Fazio usually is included in our group. However, this time he did not participate at all. Please see above for the updated members.
*/

import java.util.*;

class TaskTwo {
    public static void main(String[] args) {
        // initialize G1 and G2 with the values from the directions
        int[][] adjacencyMatrix1 = new int[][] {
            {0, 1, 1, 1, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 1, 0},
            {0, 1, 1, 0, 0, 1, 1},
            {0, 0, 0, 1, 1, 0, 1},
            {0, 0, 0, 0, 1, 1, 0}
        };
        int[][] adjacencyMatrix2 = new int[][] {
            {0, 1, 1, 1, 1, 1, 0},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 1, 0}
        };

        // create graphs G1 and G2 by reusing code from TaskOne
        Adj_List_Graph g1 = createGraph(adjacencyMatrix1);
        Adj_List_Graph g2 = createGraph(adjacencyMatrix2);

        // BFS Calls - G1
        System.out.println("BFS Calls for Graph One");
        BFS(g1, 0);
        BFS(g1, 1);
        BFS(g1, 2);
        BFS(g1, 3);
        BFS(g1, 4);
        BFS(g1, 5);
        BFS(g1, 6);

        // BFS Calls - G2
        System.out.println("BFS Calls for Graph Two");
        BFS(g2, 0);
        BFS(g2, 1);
        BFS(g2, 2);
        BFS(g2, 3);
        BFS(g2, 4);
        BFS(g2, 5);
        BFS(g2, 6);
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

    // we need a BFS algorithm here
    private static void BFS(Adj_List_Graph graph, int s) {
        // create an array to keep track of each visited node
        boolean[] visited = new boolean[graph.n];
        
        // initialize the destination to be the final node of the graph
        int dest = graph.n;

        // arrays to hold the distances and prevoius graph nodes
        int[] dist = new int[graph.n];
        int[] pred = new int[graph.n];

        // initialize the distances and predecessors
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(pred, -1);
        
        // create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // mark the current node as visited in the visited array
        visited[s] = true;

        // set the initial distance to 0
        dist[s] = 0;

        // add the curent node to the queue
        queue.add(s);

        // process the entire queue
        while (!queue.isEmpty()) {
            // remove the starting node from the queue
            s = queue.poll(); // poll retreives and removes the head of a queue
            
            // print the vertex
            System.out.print(s + " ");

            // grab all adjacent vertices of the dequeued vertex
            for (int i = 0; i < graph.adj.get(s).size(); i++) {
                // add item to the queue if we haven't touched it yet; this prevents getting stuck in an infinite cycle
                if (!visited[graph.adj.get(s).get(i)]) {
                    visited[graph.adj.get(s).get(i)] = true;
                    dist[graph.adj.get(s).get(i)] = dist[s] + 1;
                    pred[graph.adj.get(s).get(i)] = s;
                    queue.add(graph.adj.get(s).get(i));

                    // stop when dest is found
                    if (graph.adj.get(s).get(i) == dest)
                        return;
                }
            }

        }
        System.out.println();
    }
}
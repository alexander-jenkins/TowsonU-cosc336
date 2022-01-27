/*
    COSC 336.001 Assignment 4
    Team: Alexander Jenkins, Darwin Martinez-Delcid, Melvin Noguera, Michael Fazio
    Objective: Design an O(n) algorithm to find the largest possible area
    of an axis parallel rectangle that fits inside the polygon formed by the given points.
 */

// Import required packages
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Assignment4 {
    public static void main(String[] args) throws IOException {
        // get the max areas
        int area1 = findMaxArea("input-4.1.txt");
//        int area2 = findMaxArea("input-4.2.txt");
//        int area3 = findMaxArea("input-4.3.txt");
//        int area4 = findMaxArea("input-4.4.txt");

        // write out the answers
        writeAnswer("answer-4.1.txt", area1);
//        writeAnswer("answer-4.2.txt", area2);
//        writeAnswer("answer-4.3.txt", area3);
//        writeAnswer("answer-4.4.txt", area4);
    }

    // parse the array from the text time and then find the area
    private static int findMaxArea(String filename) throws IOException {
        // get all the vertices needed and the number of pairs/bars
        int[] vertices = readFile(filename);
        int numBars = (vertices[0] - 2) / 2;

        // create arrays for height and width and then fill them with the proper values
        int[] heights = new int[numBars];
        int[] widths = new int[numBars];
        int hPos = 0, wPos = 0;

        // fill heights array
        for (int i = 4; i < vertices[0] * 2 - 1; i += 4) heights[hPos++] = vertices[i];

        // fill widths array
        // width of a bar = x2-x1
        // x1 starts at vertices[3]
        for (int i = 3; i < vertices[0] * 2 -1; i+=4) widths[wPos++] = vertices[i+2] - vertices[i];

        return findMaxArea(heights, widths, numBars);
    }

    // overload findMaxArea and implement the algorithm for finding the max area
    private static int findMaxArea(int[] heights, int[] widths, int numBars) {
        // init vars
        int maxArea = 0;
        int topStack, areaWithTop;
        Stack<Integer> indices = new Stack<>();

        // run through all the bars of the histogram
        int index = 0;
        while (index < numBars) {
            // if the current bar is higher than the bar at the top of the stack, push to the stack
            if (indices.isEmpty() || heights[indices.peek()] <= heights[index])
                indices.push(index++);
            else {
                // store the index at top of the stack
                topStack = indices.peek();

                // remove the top index
                indices.pop();

                // calculate the area with heights[topStack] as the smallest bar
                areaWithTop = heights[topStack] * widths[topStack] * (indices.isEmpty() ? index : index - indices.peek() - 1);

                // update the max area if necessary
                if (maxArea < areaWithTop) maxArea = areaWithTop;
            }
        }

        // pop the rest of the bars from the stack and calculate area with each popped bar as the smallest
        while (!indices.isEmpty()) {
            topStack = indices.peek();
            indices.pop();
            areaWithTop = heights[topStack] * widths[topStack] * (indices.isEmpty() ? index : index - indices.peek() - 1);

            if (maxArea < areaWithTop) maxArea = areaWithTop;
        }

        return maxArea;
    }

    // Function to read in the data from files
    private static int[] readFile(String fileName) throws IOException {
        // open the file and create the scanner
        File rawData = new File(fileName);
        Scanner scan = new Scanner(rawData);

        // get the number of vertex pairs
        int pairs = scan.nextInt();

        // fill an array with all pairs
        int[] vertices = new int[pairs*2+1];
        vertices[0] = pairs;
        for (int i = 1; i < pairs*2; i++) vertices[i] = scan.nextInt();

        return vertices;
    }

    // Function to output the answer to a text file
    private static void writeAnswer(String fileName, int maxArea) throws IOException {
        // create the answer file, deleting if already there.
        File output = new File(fileName);
        if (output.exists()) output.delete();
        output.createNewFile();

        // write the answer to the file and close it
        FileWriter writer = new FileWriter(output);
        writer.write(String.valueOf(maxArea));
        writer.close();
    }

    // method to print arrays -- used for testing
    private static void printArray(int[] array) {
        for (int num: array) System.out.printf("%d ", num);
        System.out.println();
    }
}

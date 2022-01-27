/*
    Group Members:
    - Alexander Jenkins
    - Darwin Martinez Delcid
    - Melvin Noguera Monroy
    - Michael Fazio

    Assignment Objective:
    1) Determine whether there exists an integer "x" that occurs in the sequence more than n/2 times
    2) Determine whether there exists an integer "x" that occurs in the sequence more than n/3 times

    Hint:
    n/2 + 1 occurences means the number must be the median
*/

import java.util.*;

import javax.print.event.PrintEvent;

import java.io.*;
class Main {
    public static void main(String[] args) {
        int[] test = {1,2,3,4,5};
        System.out.println(randomizedSelect(test, 1, 2, 3));
    }

    private static int randomizedSelect(int[] A, int p, int r, int i) {
        // if the ends of the arrays are the same, return the element on the left
        if (p == r) return A[p];

        int q = randomizedPartition(A, p, r);
        int k = q - p + 1;
        
        if (i == k) return A[q]; // the pivot value is the answer
        else if (i < k) return randomizedSelect(A, p, q-1, i);
        else return randomizedSelect(A, q+1, r, i-k);
    }

    private static int randomizedPartition(int[] A, int p, int r) {
        // init random generator
        Random randomizer = new Random();
        // grab a random value between p and r
        int i = randomizer.nextInt(r) + p;
        // exhange A[r] with A[i]
        int temp = A[r];
        A[r] = A[i];
        A[i] = temp;
        // partition the array
        return partition(A, p, r);
    }

    private static int partition(int[] A, int p, int r) {
        int rightValue = A[r];
        int i = p - 1;
        // partition the array
        for (int j = p; r < r-1; j++) {
            if (A[j] <= rightValue) {
                i++;
                // exhange A[i] with A[j]
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            } 
        }
        // exchange A[i+1] with A[r]
        int temp = A[i+1];
        A[i+1] = A[r];
        A[r] = temp;
        // return the next bound
        return i++;
    }
}
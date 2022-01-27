import java.util.*;
public class MelvinProjectOne {
    public static void main(String [] args) {
    /* Melvin Noguera Monroy, Darwin Martinez, Alex Jenkins, Michael Fazio
     Program to find longest non-contiguous increasing
     sub-sequence based on a sequence submitted by user. */

        //declaration of variables
        Scanner input = new Scanner(System.in);
        int size = 1000;
        int [] tempArray = new int[size];
        int enter = 0;
        int a = 0;
        int length = 0;
        int seqLength = 0;
        int sum = 0;

        //User enters sequence of integers into a temp array while also calculating length
        System.out.println("Enter the sequence integers: (Enter -999 to exit)");
        enter = input.nextInt();
        while (enter != -999) {
            tempArray[a] = enter;
            length++;
            a++;
            enter = input.nextInt();
        }

        //Move the inputted sequence into another array with the exact length
        int [] realArray = new int[length];
        for (int j = 0; j < length; j++) {
            realArray[j] = tempArray[j];
        }

        //makes a sub array that counts the passing of each possible sub-sequence
        int actualSolution[] = new int[length];
        int [] subArray = new int[length];
        for (int j = 0; j < length; j++) {
            subArray[j] = 1;
        }

        //Algorithm to find length of longest sub-sequence and adds to each possible
        //subsequence in the subarray
        for(int i = 0; i < length; i++){
            for(int j=0; j < a; j++){
                if(realArray[j] < realArray[i]){
                    if(subArray[i]  < subArray[j] + 1){
                        subArray[i] = subArray[j] + 1;
                    }
                }
            }
        }

        //Find the greatest length from the subarray of the subsequence count
        for(int i =1; i < length; i++){
            if(subArray[i] > seqLength){
                seqLength = subArray[i];
            }
        }

        //print the actual array
        System.out.println(Arrays.toString(realArray));

        //print the found length
        System.out.println("Length: " + seqLength);
    }
}

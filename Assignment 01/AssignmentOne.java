// Alexander Jenkins, Darwin Martinez-Delcid, Melvin Noguera Monroy, Michael Fazio
// Find the longest non-contiguous increasing subsequence of a user-inputted array
import java.util.Arrays;
import java.util.Scanner;
public class AssignmentOne {
    public static void main(String[] args) {

        // Collect an array of integers from the user input.
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter your array of integers (on ONE LINE with each value separated by commas and/or whitespace.)");
        String userInput = cin.nextLine();

        // Split the input from the user into an array of Strings
        String[] preparsedInput = userInput.split("\\s*,\\s*");
        int[] numbers = new int[preparsedInput.length];

        // Put the user input into an array of integers
        int pos = 0;
        for(String num : preparsedInput) {
            numbers[pos] = Integer.parseInt(num);
            pos++;
        }
        cin.close();

        // Init a temp array full of ones and of the same length as the numbers array.
        int[] temp = new int[numbers.length];
        Arrays.fill(temp, 1);

        // Loop through the numbers array and find the subsequences
        for(int i = 1; i < numbers.length; i++)
            for (int j = 0; j < i; j++){
                if(numbers[j] < numbers[i] && temp[i] < temp[j] + 1) temp[i] = temp[j] + 1;
            }

        // Display the largest sequence
        System.out.printf("The length of the longest increasing subsequence is %d.\n", Arrays.stream(temp).max().getAsInt());
    }
}

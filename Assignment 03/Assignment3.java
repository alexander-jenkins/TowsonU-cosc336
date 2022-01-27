/*
    Alexander Jenkins, Darwin Martinez-Delcid, Melvin Noguera, Michael Fazio
    Modify MergeSort so that in addition to sorting the array it also counts the number of *-pairs


 */
public class Assignment3 {
    public static void main(String[] args) {
        int[] numbers = {1,4,2,3,3,41,1,22};
        printArray(numbers);
        numbers = mergeSort(numbers);
        printArray(numbers);
    }

    private static int[] mergeSort(int[] array) {
        // check if merge sort is possible
        if(array.length <= 1 ) return array;

        // define left and right sides of the array
        int middle = array.length / 2;
        int[] left = new int[middle];

        // create right side based on if array is of odd or even length
        int[] right;
        if (array.length % 2 == 0) right = new int[middle];
        else right = new int[middle + 1];

        // fill the left and right halves
        for (int i = 0; i < middle; i++) left[i] = array[i];
        for (int j = 0; j < right.length; j++) right[j] = array[middle + j];

        // create the new array holding the sorted result
        int[] result = new int[array.length];

        // start recursive voodoo
        left = mergeSort(left);
        right = mergeSort(right);

        // get the result array
        result = merge(left, right);

        return result;
    }

    // sort and merge two arrays together
    private static int[] merge(int[] left, int[] right) {
        // init the result array
        int[] result = new int[left.length + right.length];

        // set up pointers to see if we need to merge
        int leftPointer = 0, rightPointer = 0, resultPointer = 0;

        // make sure we still need to merge and then do it
        while (leftPointer < left.length || rightPointer < right.length) {
            // if there are still elements in both arrays, sort and add to result
            if (leftPointer < left.length && rightPointer < right.length) {
                // see if left value is smaller than right, then add to result
                if (left[leftPointer] < right[rightPointer]) {
                    result[resultPointer] = left[leftPointer];
                    resultPointer++;
                    leftPointer++;
                }
                else {
                    result[resultPointer] = right[rightPointer];
                    resultPointer++;
                    rightPointer++;
                }
            }
            // fill result with the sorted, remaining values of left
            else if (leftPointer < left.length) {
                result[resultPointer] = left[leftPointer];
                resultPointer++;
                leftPointer++;
            }
            // fill result with the sorted, remaining values of right -- if any
            else if (rightPointer < right.length) {
                result[resultPointer] = right[rightPointer];
                resultPointer++;
                rightPointer++;
            }
        }
        return result;
    }

    // Simple method for printing an array to console - used for testing
    public static void printArray(int[] array) {
        for (int num: array) System.out.printf("%d ", num);
        System.out.println();
    }
}

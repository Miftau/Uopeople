/******************************************************************************

This program does the following:

Create two arrays of type int[]. Both arrays should be the same size, 
and the size should be given by a constant in the program so that you 
can change it easily.

Fill the arrays with random integers. The arrays should have identical contents,
with the same random numbers in both arrays. 
To generate random integers with a wide range of sizes, 
you could use (int)(Integer.MAX_VALUE * Math.random()).

Sort the first array using either Selection Sort or Insertion Sort.
You should add the sorting method to your program; 
you can copy it from Section 7.4 if you want. 
(It is a good idea to check that you got the sorting method correct
by using it to sort a short array and printing out the result.)

Time how long it takes to sort the array and print out the time.

Now, sort the second (identical) array using Arrays.sort(). Again, 
time how long it takes, and print out the time.

*******************************************************************************/
import java.util.Arrays;
import java.util.Random;

public class SortArray {

    private static final int SIZE = 10000;

    public static void main(String[] args) {
        // Create two arrays of the same size.
        int[] array1 = new int[SIZE];
        int[] array2 = new int[SIZE];

        // Fill the arrays with random integers.
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            array1[i] = (int)(Integer.MAX_VALUE * random.nextDouble());
            array2[i] = array1[i];
        }

        // Sort the first array using Selection Sort.
        long startTime1 = System.nanoTime();
        selectionSort(array1);
        long endTime1 = System.nanoTime();
        long time1 = endTime1 - startTime1;

        // Sort the second array using Arrays.sort().
        long startTime2 = System.nanoTime();
        Arrays.sort(array2);
        long endTime2 = System.nanoTime();
        long time2 = endTime2 - startTime2;

        // Print the sorting time for each method.
        System.out.println("Time to sort array using Selection Sort: " + time1);
        System.out.println("Time to sort array using Arrays.sort(): " + time2);
    }

    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }
}
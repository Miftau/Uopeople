/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.Arrays;

public class SortingBenchmark {
    public static void main(String[] args) {
        final int[] arraySizes = {1000, 10000, 100000}; // Array sizes to test

        for (int size : arraySizes) {
            System.out.println("Array Size: " + size);

            // Create and fill two identical arrays with random integers
            int[] array1 = generateRandomArray(size);
            int[] array2 = Arrays.copyOf(array1, size);

            // Sorting using Selection Sort
            long startTime = System.currentTimeMillis();
            selectionSort(array1);
            long runTime = System.currentTimeMillis() - startTime;
            System.out.println("Selection Sort Time: " + (runTime / 1000.0) + " seconds");

            // Sorting using Arrays.sort()
            startTime = System.currentTimeMillis();
            Arrays.sort(array2);
            runTime = System.currentTimeMillis() - startTime;
            System.out.println("Arrays.sort() Time: " + (runTime / 1000.0) + " seconds");

            System.out.println();
        }
    }

    // Generate and fill an array with random integers
    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Integer.MAX_VALUE * Math.random());
        }
        return array;
    }

    // Selection Sort implementation
    private static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}

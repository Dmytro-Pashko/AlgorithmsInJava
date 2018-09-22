package week3;

import edu.princeton.cs.algs4.StdOut;

public class SortBenchmark {

    public static void main(String[] args) {
        new SortBenchmark();
    }

    private SortBenchmark() {
//        testRecursiveMergeSort();
//        testBottomUpMergeSort();
        testInsertionSort();
//        testSelectionSort();
    }

    private void testRecursiveMergeSort() {
        final Sort sort = new RecursiveSorting(false);
        Comparable[] array = generateSortingArray(100000);
        long startTime = System.currentTimeMillis();
        sort.sort(array);
        StdOut.println(String.format("Recursive merge sorting took %d ms.", System.currentTimeMillis() - startTime));
    }

    private void testBottomUpMergeSort() {
        final Sort sort = new BottomUpSorting(false);
        Comparable[] array = generateSortingArray(100000000);
        long startTime = System.currentTimeMillis();
        sort.sort(array);
        StdOut.println(String.format("BottomUp merge sorting took %d ms.", System.currentTimeMillis() - startTime));
    }

    private void testInsertionSort() {
        final Sort sort = new InsertionSort(true);
        Comparable[] array = generateSortingArray(10);
        long startTime = System.currentTimeMillis();
        sort.sort(array);
        StdOut.println(String.format("Insertion sorting took %d ms.", System.currentTimeMillis() - startTime));
    }

    private void testSelectionSort() {
        final Sort sort = new SelectionSort(false);
        Comparable[] array = generateSortingArray(100000);
        long startTime = System.currentTimeMillis();
        sort.sort(array);
        StdOut.println(String.format("Selection sorting took %d ms.", System.currentTimeMillis() - startTime));
    }

    private Comparable[] generateSortingArray(final int size) {
        Comparable[] array = new Comparable[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }
}

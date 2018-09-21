package week3;

import edu.princeton.cs.algs4.StdOut;

public class MergeSortBenchmark {

    public static void main(String[] args) {
        new MergeSortBenchmark();
    }

    private MergeSortBenchmark() {
        testRecursiveMergeSort();
        testBottomUpMergeSort();
    }

    private void testRecursiveMergeSort() {
        final MergeSort sort = new RecursiveMergeSorting(false);
        Comparable[] array = generateSortingArray(100000000);
        long startTime = System.currentTimeMillis();
        sort.sort(array);
        StdOut.println(String.format("Recursive merge sorting took %d ms.", System.currentTimeMillis() - startTime));
    }

    private void testBottomUpMergeSort() {
        final MergeSort sort = new BottomUpMergeSorting(false);
        Comparable[] array = generateSortingArray(100000000);
        long startTime = System.currentTimeMillis();
        sort.sort(array);
        StdOut.println(String.format("BottomUp merge sorting took %d ms.", System.currentTimeMillis() - startTime));
    }

    private Comparable[] generateSortingArray(final int size) {
        Comparable[] array = new Comparable[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }
}

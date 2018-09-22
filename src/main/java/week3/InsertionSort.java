package week3;

import edu.princeton.cs.algs4.StdOut;
import utils.ArrayUtils;

import java.util.Comparator;

public class InsertionSort implements Sort {

    private final boolean isDebug;

    InsertionSort(boolean isDebug) {
        this.isDebug = isDebug;
    }

    @Override
    public void sort(Object[] array, Comparator comparator) {

        for (int i = 0; i < array.length; i++) {

            for (int j = i; j > 0 && comparator.compare(array[j], array[j - 1]) < 0; j--) {
                swap(array, j, j - 1);
                if (isDebug) {
                    StdOut.println("Sorted array = " + ArrayUtils.getFormattedArray(array));
                }
            }
            if (isDebug) {
                StdOut.println("Sorted array = " + ArrayUtils.getFormattedArray(array));
            }
        }
    }

    public void sort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {

            for (int j = i; j > 0 && isLess(array, j, j - 1); j--) {
                System.out.println("swaping " + array[j] + " and " + array[j-1]);
                swap(array, j, j - 1);
                if (isDebug) {
                    StdOut.println("Sorted array = " + ArrayUtils.getFormattedArray(array));
                }
            }
        }
    }

    private boolean isLess(Comparable[] src, int firstIndex, int secondIndex) {
        return src[firstIndex].compareTo(src[secondIndex]) < 0;
    }

    private void swap(Object[] src, int firstIndex, int secondIndex) {
        Object buf = src[firstIndex];
        src[firstIndex] = src[secondIndex];
        src[secondIndex] = buf;
    }
}

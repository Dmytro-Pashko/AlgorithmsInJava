package week3;

import edu.princeton.cs.algs4.StdOut;
import utils.ArrayUtils;

import java.util.Comparator;

public class SelectionSort implements Sort {

    private final boolean isDebug;

    SelectionSort(boolean isDebug) {
        this.isDebug = isDebug;
    }

    @Override
    public void sort(Object[] array, Comparator comparator) {

    }

    @Override
    public void sort(Comparable[] array) {

        for (int i = 0; i < array.length; i++) {

            int minValueIndex = i;
            for (int j = i + 1; j < array.length; j++) {

                if (isLess(array, j, minValueIndex)) {
                    minValueIndex = j;
                }
            }
            swap(array, i, minValueIndex);
        }
        if (isDebug) {
            StdOut.println("Sorted array = " + ArrayUtils.getFormattedArray(array));
        }
    }

    private boolean isLess(Comparable[] src, int firstIndex, int secondIndex) {
        return src[firstIndex].compareTo(src[secondIndex]) < 0;
    }

    private void swap(Comparable[] src, int firstIndex, int secondIndex) {
        Comparable buf = src[firstIndex];
        src[firstIndex] = src[secondIndex];
        src[secondIndex] = buf;
    }
}

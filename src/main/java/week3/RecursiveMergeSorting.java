package week3;

import edu.princeton.cs.algs4.StdOut;
import utils.ArrayUtils;

import java.util.Comparator;

public class RecursiveMergeSorting implements MergeSort {

    private final boolean isDebug;
    private Merger merger;

    RecursiveMergeSorting(final boolean isDebug) {
        this.isDebug = isDebug;
    }

    @Override
    public void sort(Object[] array, Comparator comparator) {
        merger = new Merger(comparator);
        Object[] out = new Object[array.length];
        sort(array, out, 0, array.length - 1);
    }

    @Override
    public void sort(Comparable[] array) {
        merger = new Merger();
        Comparable[] out = new Comparable[array.length];
        sort(array, out, 0, array.length - 1);
    }

    private void sort(Object[] src, Object[] out, int lowIndex, int highIndex) {
        if (highIndex <= lowIndex) return;
        int middle = lowIndex + (highIndex - lowIndex) / 2;
        sort(src, out, lowIndex, middle);
        sort(src, out, middle + 1, highIndex);
        merger.merge(src, out, lowIndex, middle, highIndex);
        if (isDebug) {
            StdOut.println("Sorted array : " + ArrayUtils.getFormattedArray(src));
        }
    }

    private void sort(Comparable[] src, Comparable[] out, int lowIndex, int highIndex) {
        if (highIndex <= lowIndex) return;
        int middle = lowIndex + (highIndex - lowIndex) / 2;
        sort(src, out, lowIndex, middle);
        sort(src, out, middle + 1, highIndex);
        merger.merge(src, out, lowIndex, middle, highIndex);
        if (isDebug) {
            StdOut.println("Sorted array : " + ArrayUtils.getFormattedArray(src));
        }
    }
}

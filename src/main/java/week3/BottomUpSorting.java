package week3;

import edu.princeton.cs.algs4.StdOut;
import utils.ArrayUtils;

import java.util.Comparator;

public class BottomUpSorting implements Sort {

    private final boolean isDebug;
    private Merger merger;

    BottomUpSorting(final boolean isDebug) {
        this.isDebug = isDebug;
    }

    @Override
    public void sort(Comparable[] src) {
        merger = new Merger();
        Comparable[] out = new Comparable[src.length];

        for (int groupSize = 1; groupSize < src.length; groupSize += groupSize) {
            if (isDebug) {
                StdOut.println("Merging groups with size = " + groupSize * 2);
            }
            for (int startIndex = 0; startIndex <= src.length - 1; startIndex += groupSize * 2) {
                int endIndex = Math.min(startIndex + groupSize * 2 - 1, out.length - 1);
                if (isDebug) {
                    StdOut.println("Sorting group " + ArrayUtils.getFormattedArray(src, startIndex, endIndex));
                }
                int midIndex = startIndex + groupSize - 1;
                merger.merge(src, out, startIndex, midIndex, endIndex);
                if (isDebug) {
                    StdOut.println("Out " + ArrayUtils.getFormattedArray(out, 0, out.length - 1));
                }
            }
        }
        if (isDebug) {
            StdOut.println("Sorted array : " + ArrayUtils.getFormattedArray(src));
        }
    }

    @Override
    public void sort(Object[] src, Comparator comparator) {
        merger = new Merger(comparator);
        Object[] out = new Object[src.length];

        for (int groupSize = 1; groupSize < src.length; groupSize += groupSize) {
            if (isDebug) {
                StdOut.println("Merging groups with size = " + groupSize * 2);
            }
            for (int startIndex = 0; startIndex <= src.length - 1; startIndex += groupSize * 2) {
                int endIndex = Math.min(startIndex + groupSize * 2 - 1, out.length - 1);
                if (isDebug) {
                    StdOut.println("Sorting group " + ArrayUtils.getFormattedArray(src, startIndex, endIndex));
                }
                int midIndex = startIndex + groupSize - 1;
                merger.merge(src, out, startIndex, midIndex, endIndex);
                if (isDebug) {
                    StdOut.println("Out " + ArrayUtils.getFormattedArray(out, 0, out.length - 1));
                }
            }
        }
        if (isDebug) {
            StdOut.println("Sorted array : " + ArrayUtils.getFormattedArray(src));
        }
    }
}

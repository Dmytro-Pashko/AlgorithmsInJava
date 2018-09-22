package week3;


import edu.princeton.cs.algs4.LinkedBag;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.ArrayUtils;

import java.util.Arrays;
import java.util.Iterator;

public class InterviewQuestions {

    public static void main(String[] args) {
        InterviewQuestions tasks = new InterviewQuestions();
//        tasks.task1();
//        tasks.task2();
        tasks.task3();
    }

    /**
     * Merging with smaller auxiliary array. Suppose that the subarray a[0]
     * to a[n-1] is sorted and the subarray a[n] to a[2*n−1] is sorted.
     * How can you merge the two subarrays so that a[0] to a[2*n−1] is sorted
     * using an auxiliary array of length n (instead of 2n)?
     */
    void task1() {
        int n = 5;
        Integer[] first = generateSortingArray(n);
        Arrays.sort(first);
        StdOut.println("First half =" + ArrayUtils.getFormattedArray(first));

        Integer[] second = generateSortingArray(n);
        Arrays.sort(second);
        StdOut.println("Second half =" + ArrayUtils.getFormattedArray(second));

        Integer[] array = new Integer[2 * n];
        System.arraycopy(first, 0, array, 0, n);
        System.arraycopy(second, 0, array, n, n);
        StdOut.println("Merging array =" + ArrayUtils.getFormattedArray(array));
        merge(array);
        StdOut.println("Merged array =" + ArrayUtils.getFormattedArray(array));
    }

    private void merge(Comparable[] array) {

        int mid = array.length / 2;
        Comparable[] out = new Comparable[array.length / 2];
        System.arraycopy(array, 0, out, 0, out.length);

        //index for first part in array(in auxiliary array).
        int outIndex = 0;
        //index for second part of array
        int j = mid;

        for (int k = 0; k < array.length; k++) {
            if (outIndex > j) array[k] = array[j++];
            else if (j > array.length - 1) array[k] = out[outIndex++];
            else if (array[j].compareTo(out[outIndex]) < 0) array[k] = array[j++];
            else array[k] = out[outIndex++];
        }
    }

    private Integer[] generateSortingArray(final int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = StdRandom.uniform(100);
        }
        return array;
    }

    /**
     * Counting inversions. An inversion in an array a[] is a pair of entries a[i] and a[j]
     * such that i < j but a[i] > a[j]. Given an array, design a linearithmic algorithm
     * to count the number of inversions.
     * <p>
     * If array is sorted, then number of inversions are 'zero'.
     * The simplest way to count the inversion is for each element, count number of elements which are on right side of
     * it and are smaller than it. I am attaching a useful infographic on counting the number of inversions in an
     * array by modifying Merge-Sort slightly.
     * <p>
     * Input array [3, 5, 2, 6, 1, 3]
     * <p>
     * Inversions : 3-2, 3-1, 5-2, 5-1, 5-3, 2-1, 6-1, 6-3 = 8
     */
    void task2() {
//        Comparable[] array = generateSortingArray(10);//
        Comparable[] array = {3, 5, 2, 6, 1, 3};
        StdOut.println("Array =" + ArrayUtils.getFormattedArray(array));
        int inversions = sort(array);
        System.out.println("Inversions = " + inversions);

    }

    private int sort(Comparable[] array) {
        return sort(array, new Comparable[array.length], 0, array.length - 1);
    }

    private int sort(Comparable[] src, Comparable[] out, int startIndex, int endIndex) {

        if (endIndex == startIndex) return 0;
        int mid = startIndex + (endIndex - startIndex) / 2;
        int rightInversions = sort(src, out, startIndex, mid);
        int leftInversions = sort(src, out, mid + 1, endIndex);
        int inversions = merge(src, out, startIndex, mid, endIndex);
        System.out.println("Sotred array=" + ArrayUtils.getFormattedArray(src));
        return rightInversions + leftInversions + inversions;
    }

    private int merge(Comparable[] src, Comparable[] out, int lowIndex, int midIndex, int heightIndex) {

        for (int k = lowIndex; k <= heightIndex; k++) {
            out[k] = src[k];
        }

        int i = lowIndex;
        int j = midIndex + 1;
        int inversions = 0;
        StdOut.println(String.format("Merge array=%s, left=%s right=%s",
                ArrayUtils.getFormattedArray(out),
                ArrayUtils.getFormattedArray(out, i, midIndex),
                ArrayUtils.getFormattedArray(out, j, heightIndex)));

        for (int k = lowIndex; k <= heightIndex; k++) {
            if (i > midIndex) {
                src[k] = out[j++];
            } else if (j > heightIndex) {
                src[k] = out[i++];
            } else if (out[i].compareTo(out[j]) <= 0) {
                //left item less than right
                src[k] = out[i++];
            } else {
                StdOut.println(String.format("Inversion [%s-%s]", out[i].toString(), out[j].toString()));
                //Current a[i] item and all previous a[0..i-1] items bigger than j item.
                inversions += midIndex - i + 1;
                src[k] = out[j++];
            }
        }
        return inversions;
    }


    /**
     * Shuffling a linked list. Given a singly-linked list containing n items, rearrange the items uniformly at random.
     * Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional
     * to n*logn in the worst case.
     */
    void task3() {
        int n = 10;
        LinkedBag<Integer> list = new LinkedBag<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        printList(list);
        printList(shuffle(list));
    }

    private void printList(LinkedBag<Integer> list) {
        Iterator i = list.iterator();
        StringBuilder sb = new StringBuilder("[");
        while (i.hasNext()){
            sb.append(i.next());
            if (i.hasNext()){
                sb.append(", ");
            }
        }
        sb.append("]");
        StdOut.println(sb.toString());
    }

    private  <T> LinkedBag<T> shuffle(LinkedBag<T> list) {
        if (list.size() <= 1) return list;

        //Split list to two sublists.
        Pair<LinkedBag<T>,LinkedBag<T>> sublists = split(list);

        LinkedBag<T> left = shuffle(sublists.first);
        LinkedBag<T> right = shuffle(sublists.second);

        return merge(left, right);
    }

    //Used O(N) extra space to create two sublists a[0..N/2] and a[N/2+1..N]
    private <T>Pair<LinkedBag<T>, LinkedBag<T>> split(LinkedBag<T> list) {
        LinkedBag<T> left = new LinkedBag<>();
        LinkedBag<T> right = new LinkedBag<>();

        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            //If random value true, then put into first sublist.
            if (StdRandom.bernoulli())
                left.add(it.next());
            else
                right.add(it.next());
        }
        return new Pair<>(left, right);
    }

    private <T> LinkedBag<T> merge(LinkedBag<T> left, LinkedBag<T> right) {
        for (T aRight : right) {
            left.add(aRight);
        }
        return left;
    }

    private final class Pair<V,K>{

        V first;
        K second;

        Pair(V first, K second) {
            this.first = first;
            this.second = second;
        }

    }
}

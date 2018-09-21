package week3;

public class InsertionSort {

    public static void main(String[] args) {

    }


    public void sort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {

            for (int j = i; j > 0 && isLess(array, j, j - 1); j--) {
                swap(array, j, j - 1);
            }
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

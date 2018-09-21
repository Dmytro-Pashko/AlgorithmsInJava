package week3;

import java.util.Comparator;

class Merger {

    private Comparator comparator;

    Merger() {
    }

    Merger(final Comparator comparator) {
        this.comparator = comparator;
    }

    void merge(Comparable[] src, Comparable[] out, int lowIndex, int midIndex, int heightIndex) {
        System.arraycopy(src, lowIndex, out, lowIndex, heightIndex + 1 - lowIndex);

        int i = lowIndex;
        int j = midIndex + 1;

        for (int k = lowIndex; k <= heightIndex; k++) {
            if (i > midIndex) src[k] = out[j++];
            else if (j > heightIndex) src[k] = out[i++];
            else if (out[j].compareTo(out[i]) < 0) src[k] = out[j++];
            else src[k] = out[i++];
        }
    }

    void merge(Object[] src, Object[] out, int lowIndex, int midIndex, int heightIndex) {

        System.arraycopy(src, lowIndex, out, lowIndex, heightIndex + 1 - lowIndex);

        int i = lowIndex;
        int j = midIndex + 1;

        for (int k = lowIndex; k <= heightIndex; k++) {
            if (i > midIndex)                                   src[k] = out[j++];
            else if (j > heightIndex)                           src[k] = out[i++];
            else if (comparator.compare(out[j], out[i]) < 0)    src[k] = out[j++];
            else                                                src[k] = out[i++];
        }
    }
}

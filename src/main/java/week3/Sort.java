package week3;

import java.util.Comparator;

public interface Sort {

    void sort(Object[] array, Comparator comparator);

    void sort(Comparable[] array);
}

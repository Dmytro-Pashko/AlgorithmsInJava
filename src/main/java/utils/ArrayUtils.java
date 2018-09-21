package utils;

import java.util.StringJoiner;

public class ArrayUtils {

    public static String getFormattedArray(Object[] arr) {
        return getFormattedArray(arr, 0, arr.length - 1);
    }

    public static String getFormattedArray(Object[] arr, int startIndex, int endIndex) {
        StringJoiner joiner = new StringJoiner(",", "[", "]");

        for (int elementIndex = startIndex; elementIndex <= endIndex; elementIndex++) {
            if (arr[elementIndex] != null) {
                joiner.add(arr[elementIndex].toString());
            } else {
                joiner.add("null");
            }
        }
        return joiner.toString();
    }
}

package week3.colinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments;

    /**
     * Corner cases. Throw a java.lang.IllegalArgumentException if the argument to the constructor is null,
     * if any point in the array is null, or if the argument to the constructor contains a repeated point.
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        calculateSegments(getPointsList(points));
    }

    private Point[] getPointsList(final Point[] points) {
        //Check null points.
        if (Arrays.stream(points).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Point can't be null.");
        }

        Point[] aux = new Point[points.length];
        System.arraycopy(points, 0, aux, 0, points.length);

        //Sorting
        Arrays.sort(aux);
        //Check duplicates.
        for (int i = 0; i < aux.length - 1; i++) {
            if (aux[i].compareTo(aux[i + 1]) == 0) {
                throw new IllegalArgumentException("Points can't be duplicates.");
            }
        }
        return aux;
    }

    private void calculateSegments(Point[] points) {
        segments = new ArrayList<>();
        //TODO implement this method.
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}

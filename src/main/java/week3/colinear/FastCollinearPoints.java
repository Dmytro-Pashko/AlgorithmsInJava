package week3.colinear;

import java.util.*;

public class FastCollinearPoints {

    private LineSegment[] segments;

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
        for (int i = 0; i < aux.length - 1; i++) {
            if (aux[i].compareTo(aux[i + 1]) == 0) {
                throw new IllegalArgumentException("Points can't be duplicates.");
            }
        }
        return aux;
    }

    private void calculateSegments(Point[] points) {
        List<LineSegment> maxLineSegments = new ArrayList<>();
        for (Point current : points) {
            Point[] pointsBySlope = points.clone();
            Arrays.sort(pointsBySlope, current.slopeOrder());
            maxLineSegments.addAll(calculateCollinearPoints(current, pointsBySlope));
        }
        segments = maxLineSegments.toArray(new LineSegment[0]);
    }

    /**
     * Calculate points with the same slope to current point.
     * Creating Segment when list of points with the same slope have more that 3 items.
     */
    private List<LineSegment> calculateCollinearPoints(Point current, Point[] pointsBySlope) {
        List<LineSegment> segments = new ArrayList<>();
        int x = 1;
        while (x < pointsBySlope.length) {

            LinkedList<Point> candidates = new LinkedList<>();
            final double SLOPE_REF = current.slopeTo(pointsBySlope[x]);
            do {
                candidates.add(pointsBySlope[x++]);
            } while (x < pointsBySlope.length && current.slopeTo(pointsBySlope[x]) == SLOPE_REF);

            if (candidates.size() >= 3
                    && current.compareTo(candidates.peek()) < 0) {
                Point min = current;
                Point max = candidates.removeLast();
                segments.add(new LineSegment(min, max));
            }
        }
        return segments;
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
    }
}

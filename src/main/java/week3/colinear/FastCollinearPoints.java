package week3.colinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        for (int i = 0; i < points.length - 1; i++) {
            Point current = points[i];
            //Sort next points in list by slope according to current point.
            Point[] pointsBySlope = points.clone();
            Arrays.sort(pointsBySlope, current.slopeOrder());
            calculateCollinearPoints(current, pointsBySlope);
        }
    }

    /**
     * Calculate points with the same slope to current point.
     * Creating Segment when list of points with the same slope have more that 3 items.
     */
    private void calculateCollinearPoints(Point current, Point[] pointsSortedBySlope) {
        List<Point> slopePoints = new ArrayList<>();

        double prevSlope = Double.NEGATIVE_INFINITY;

        for (int j = 1; j < pointsSortedBySlope.length; j++) {

            double slope = current.slopeTo(pointsSortedBySlope[j]);
            //if slope the same as prev point slope.
            if (slope == prevSlope) {
                slopePoints.add(pointsSortedBySlope[j]);
            } else {
                if (slopePoints.size() >= 3) {
                    createSegment(current, slopePoints);
                }
                slopePoints.clear();
                slopePoints.add(pointsSortedBySlope[j]);
            }
            prevSlope = slope;
        }
        if (slopePoints.size() >= 3) {
            createSegment(current, slopePoints);
        }
    }

    private void createSegment(Point current, List<Point> points) {

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

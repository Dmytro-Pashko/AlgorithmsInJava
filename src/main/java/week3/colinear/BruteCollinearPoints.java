package week3.colinear;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BruteCollinearPoints {

    private ArrayList<LineSegment> segments;

    /**
     * Corner cases. Throw a java.lang.IllegalArgumentException if the argument to the constructor is null,
     * if any point in the array is null, or if the argument to the constructor contains a repeated point.
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        calculateSegments(getPointsList(points));
    }

    private List<Point> getPointsList(final Point[] points) {
        ArrayList<Point> list = new ArrayList<>();
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException("Point can't be null.");
            }
            if (!list.contains(point)) {
                list.add(point);
            } else {
                throw new IllegalArgumentException("Points can't be duplicates.");
            }
        }
        Collections.sort(list);
        return list;
    }

    /**
     * If 4 points appear on a line segment in the order p→q→r→s, then you should include either the line segment
     * p→s or s→p (but not both) and you should not include subsegments such as p→r or q→r. For simplicity,
     * we will not supply any input to BruteCollinearPoints that has 5 or more collinear points.
     */
    private void calculateSegments(List<Point> points) {
        segments = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                for (int k = j + 1; k < points.size(); k++) {
                    for (int l = k + 1; l < points.size(); l++) {
                        Point p = points.get(i);
                        Point q = points.get(j);
                        Point r = points.get(k);
                        Point s = points.get(l);
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
                            segments.add(new LineSegment(p, s));
                        }

                    }
                }
            }
        }
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

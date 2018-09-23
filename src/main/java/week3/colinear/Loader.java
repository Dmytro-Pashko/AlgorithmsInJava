package week3.colinear;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import utils.ResourceLoader;

import java.io.FileNotFoundException;

/**
 * Implementation of task:
 * http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
 */
public class Loader {

    public static void main(String[] args) throws FileNotFoundException {
        String file = ResourceLoader.loadResource("colinear/input400.txt");
        In in = new In(file);
        Point[] points = readPoints(in);
        drawPoints(points);
        startBruteCollinear(points);
    }

    private static Point[] readPoints(In in) {
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        return points;
    }

    private static void startBruteCollinear(Point[] points) {
        long startTime = System.currentTimeMillis();
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        StdOut.println(String.format("Calculation take=%d ms", System.currentTimeMillis() - startTime));

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdOut.println("Segments count=" + collinear.numberOfSegments());
        StdDraw.show();
    }

    private static void drawPoints(Point[] points) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
    }
}

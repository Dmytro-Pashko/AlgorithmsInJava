package week3.colinear;

public class LineSegment {

    private final Point start;   // one endpoint of this line segment
    private final Point end;   // the other endpoint of this line segment

    /**
     * Initializes a new line segment.
     *
     * @param  start one endpoint
     * @param  end the other endpoint
     * @throws NullPointerException if either <tt>start</tt> or <tt>end</tt>
     *         is <tt>null</tt>
     */
    public LineSegment(final Point start, final Point end) {
        if (start == null || end == null) {
            throw new NullPointerException("argument is null");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Draws this line segment to standard draw.
     */
    public void draw() {
        start.drawTo(end);
    }

    /**
     * Returns a string representation of this line segment
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this line segment
     */
    public String toString() {
        return start + " -> " + end;
    }

    /**
     * Throws an exception if called. The hashCode() method is not supported because
     * hashing has not yet been introduced in this course. Moreover, hashing does not
     * typically lead to good *worst-case* performance guarantees, as required on this
     * assignment.
     *
     * @throws UnsupportedOperationException if called
     */
    public int hashCode() {
        throw new UnsupportedOperationException();
    }
}

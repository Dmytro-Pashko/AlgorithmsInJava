package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{

    private final int size;
    private final WeightedQuickUnionUF unionUF;
    private int openSitesCount = 0;
    private boolean[][] open;
    private byte top = 0;
    private int bottom;

    public Percolation(int size) {
        if (size < 1) {
            throw new IllegalArgumentException();
        }
        this.size = size;
        this.bottom = size * size + 1;
        this.unionUF = new WeightedQuickUnionUF(size * size + 2);
        this.open = new boolean[size][size];
    }

    public void open(int row, int col) {
        checkArguments(row, col);
        if (!isValid(row, col)) {
            throw new IllegalArgumentException();
        }
        connect(row, col);
        open[row - 1][col - 1] = true;
        openSitesCount++;
    }

    private void connect(int row, int col) {
        int currentCeil = toVectorPosition(row, col);

        if (row == 1) {
            unionUF.union(currentCeil, top);
        }

        if (row == size) {
            unionUF.union(currentCeil, bottom);
        }

        if (row > 1 && isOpen(row - 1, col)) {
            unionUF.union(currentCeil, toVectorPosition(row - 1, col));
        }

        if (row < size && isOpen(row + 1, col)) {
            unionUF.union(currentCeil, toVectorPosition(row + 1, col));
        }

        if (col > 1 && isOpen(row, col - 1)) {
            unionUF.union(currentCeil, toVectorPosition(row, col - 1));
        }

        if (col < size && isOpen(row, col + 1)) {
            unionUF.union(currentCeil, toVectorPosition(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        checkArguments(row, col);
        return open[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        checkArguments(row, col);
        return isOpen(row, col) && unionUF.connected(toVectorPosition(row, col), 0);
    }

    private int toVectorPosition(int row, int coll) {
        return size * (row - 1) + coll;
    }

    public int numberOfOpenSites() {
        return openSitesCount;
    }

    public boolean percolates() {
        return unionUF.connected(top, bottom);
    }

    private void checkArguments(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValid(int row, int col) {
        return row > 0 && row <= size && col > 0 && col <= size;
    }
}

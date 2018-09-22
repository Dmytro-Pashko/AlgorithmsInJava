package week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] experiments;

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(N, T);

        StdOut.println("mean                    = " + p.mean());
        StdOut.println("stddev                  = " + p.stddev());
        StdOut.println(
                "95% confidence interval = [" + p.confidenceLo() + ", " + p.confidenceHi() + "]");
    }

    public PercolationStats(int n, int trials) {
        checkArguments(n, trials);
        experiments = new double[trials];

        for (int i = 0; i < trials; i++) {
            experiments[i] = doExperiment(n);
        }
    }

    private double doExperiment(int size) {
        Percolation p = new Percolation(size);

        int sites = 0;
        while (!p.percolates()) {
            int i = StdRandom.uniform(1, size + 1);
            int j = StdRandom.uniform(1, size + 1);

            if (!p.isOpen(i, j)) {
                p.open(i, j);
                sites++;
            }
        }
        return (double) sites / (size * size);
    }

    private void checkArguments(int n, int trails) {
        if (n <= 0 && trails <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public double mean() {
        return StdStats.mean(experiments);
    }


    public double stddev() {
        return StdStats.stddev(experiments);
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(experiments.length));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(experiments.length));
    }
}

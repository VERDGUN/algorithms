package com.github.verdgun.queue;

import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.StdOut;

public class IndexMinPQTest {
    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst","a"};

/*        IndexMinPQ<String> pq = new IndexMinPQ<>(strings.length, String.class);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.delMin();
            System.out.println(i + " " + strings[i]);
        }
        System.out.println();*/
        Heap.sort(strings);
        show(strings);
    }
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
}

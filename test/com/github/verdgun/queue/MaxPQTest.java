package com.github.verdgun.queue;

import java.util.HashSet;
import java.util.Random;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class MaxPQTest {
    @org.junit.jupiter.api.Test
    void sort() {
        int n = 10;
        MaxPQ<Integer> maxPQ = new MaxPQ<>(n, Integer::compareTo);
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            maxPQ.insert(random.nextInt(100));
        }

        final StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (int i = 0; i < n; i++) {
            final String newElement = maxPQ.delMax().toString();
            System.out.println(newElement);
            stringJoiner.add(newElement);
        }
        System.out.println(stringJoiner);
    }
}

package com.github.verdgun.sort;

import java.util.HashSet;
import java.util.Random;

public class HeapSortTest {
    @org.junit.jupiter.api.Test
    void sort() {
        int n = 30;
        HashSet<Integer> integers = new HashSet<>();
        while (integers.size() < n) {
            Random random = new Random(System.currentTimeMillis());
            integers.add(random.nextInt(10000));
        }
        System.out.println(integers);
        Integer[] a = (Integer[]) integers.toArray(new Integer[]{});

/*        InsertSort selectionSort = new InsertSort();
        selectionSort.sort(a);
        selectionSort.show(a);*/

        (new HeapSort()).sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
    }
}

package com.github.verdgun.sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class HeapSortTest {
    @org.junit.jupiter.api.Test
    void sort() {
        Integer[] integers = new Integer[10];
        Random random = new Random(System.currentTimeMillis());
        integers[0] = random.nextInt(1000);
        for (int index = 1; index < integers.length; index++) {
            integers[index] = random.nextInt(1000);
        }
        System.out.println(Arrays.toString(integers));

/*        InsertSort selectionSort = new InsertSort();
        selectionSort.sort(integers);
        selectionSort.show(integers);*/

        (new HeapSort<Integer>()).sort(integers);
        System.out.println(Arrays.toString(integers));
    }
}

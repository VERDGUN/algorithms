package com.github.verdgun.sort;

import java.util.HashSet;
import java.util.Random;

class SelectionSortTest {

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

        (new SelectionSort<Integer>()).sort(a);
        for (Integer integer : a) {
            System.out.print(integer + ", ");
        }
    }
}
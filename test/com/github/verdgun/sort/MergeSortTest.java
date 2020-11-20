package com.github.verdgun.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    public void test() {
        Integer[] integers = {1, 54, 32, 12, 55, 88, 53};
        MergeSort<Integer> mergeSort = new MergeSort<>();
        System.out.println(Arrays.toString(mergeSort.sort(integers)));
    }
}
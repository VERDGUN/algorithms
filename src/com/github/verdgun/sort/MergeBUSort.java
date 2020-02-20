package com.github.verdgun.sort;

/**
 * 自底向上的归并排序
 */
public class MergeBUSort extends MergeSort {
    @Override
    public Comparable[] sort(Comparable[] unsorted) {
        for (int size = 1; size < unsorted.length; size += size) {
            for (int j = 0; j < unsorted.length - size; j += 2 * size) {
                merge(unsorted, j, j + size - 1, Math.min(j + 2 * size - 1, unsorted.length - 1));
            }
        }

        return unsorted;
    }
}

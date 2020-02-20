package com.github.verdgun.sort;


/**
 * 选择排序
 */
public class SelectionSort implements ISort {
    @Override
    public Comparable[] sort(Comparable[] unsorted) {
        for (int i = 0; i < unsorted.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < unsorted.length; j++) {
                if (less(unsorted[j], unsorted[min])) {
                    min = j;
                }
            }

            exch(unsorted, min, i);
        }

        return unsorted;
    }

}

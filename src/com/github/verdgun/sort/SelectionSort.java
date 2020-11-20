package com.github.verdgun.sort;


/**
 * 选择排序
 */
public class SelectionSort<T extends Comparable<T>> implements ISort<T> {
    @Override
    public T[] sort(T[] unsorted) {
        for (int i = 0; i < unsorted.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < unsorted.length; j++) {
                if (less(unsorted[j], unsorted[min])) {
                    min = j;
                }
            }

            exchange(unsorted, min, i);
        }

        return unsorted;
    }

}

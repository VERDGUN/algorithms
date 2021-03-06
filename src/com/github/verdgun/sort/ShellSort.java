package com.github.verdgun.sort;

/**
 * 希尔排序
 */
public class ShellSort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public T[] sort(T[] unsorted) {
        int length = unsorted.length;
        int h = 1;
        int hope = length / 3;
        while (h < hope) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < length; i++) {
                T currentElem = unsorted[i];
                int j = i;
                for (; j >= h; j -= h) {
                    if (lessThen(currentElem, unsorted[j - h])) {
                        //较大的元素向右移动一位
                        unsorted[j] = unsorted[j - h];
                    } else {
                        break;
                    }
                }
                unsorted[j] = currentElem;
            }

            h /= 3;
        }

        return unsorted;
    }
}


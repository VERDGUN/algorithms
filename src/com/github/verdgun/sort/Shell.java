package com.github.verdgun.sort;

public class Shell implements ISort {

    @Override
    public Comparable[] sort(Comparable[] unsorted) {
        int length = unsorted.length;
        int h = 1;
        while (h < length / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < length; i++) {
                Comparable currentElem = unsorted[i];
                int j = i;
                for (; j >= h; j -= h) {
                    if (less(currentElem, unsorted[j - h])) {
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


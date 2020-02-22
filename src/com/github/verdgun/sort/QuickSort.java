package com.github.verdgun.sort;

/**
 * 快速排序(普通版)
 */
public class QuickSort implements ISort {
    @Override
    public Comparable[] sort(Comparable[] unsorted) {
        return sort(unsorted, 0, unsorted.length - 1);
    }

    private Comparable[] sort(Comparable[] unsorted, int lo, int hi) {
        if (lo >= hi) return unsorted;
        //执行一次partition后unsorted[lo...(j-1)]均小于等于unsorted[j],unsorted[(j+1)...hi]大于等于unsorted[j]
        int j = partition(unsorted, lo, hi);

        sort(unsorted, lo, j - 1);
        sort(unsorted, j + 1, hi);
        return unsorted;
    }

    private int partition(Comparable[] unsorted, int lo, int hi) {
        Comparable v = unsorted[lo];
        int i = lo, j = hi + 1;
        //循环结束时unsorted[1...j]均小于等于v,unsorted[(j+1),hi]均大于等于v.
        while (true) {
            //此内循环结束时unsorted[j]>=v
            while (less(unsorted[++i], v)) if (i == hi) break;

            //此内循环结束时unsorted[j]<=v
            while (less(v, unsorted[--j])) if (j == lo) break;

            if (i >= j) break;
            exch(unsorted, i, j);
        }

        //将v和unsorted[j]互换,保证unsorted[lo...(j-1)]均小于等于v,unsorted[(j+1)...hi]大于等于v
        exch(unsorted, lo, j);

        return j;
    }
}

package com.github.verdgun.sort;

/**
 * 快速排序(普通版)
 */
public class QuickSort<T extends Comparable<T>> implements ISort<T> {
    @Override
    public T[] sort(T[] unsorted) {
        return sort(unsorted, 0, unsorted.length - 1);
    }

    private T[] sort(T[] unsorted, int lo, int hi) {
        if (lo >= hi) return unsorted;
        //执行一次partition后unsorted[lo...(j-1)]均小于等于unsorted[j],unsorted[(j+1)...hi]大于等于unsorted[j]
        int j = partition(unsorted, lo, hi);

        sort(unsorted, lo, j - 1);
        sort(unsorted, j + 1, hi);
        return unsorted;
    }

    private int partition(T[] unsorted, int lo, int hi) {
        T v = unsorted[lo];
        int i = lo, j = hi + 1;
        //循环结束时unsorted[1...j]均小于等于v,unsorted[(j+1),hi]均大于等于v.
        while (true) {
            //此内循环结束时unsorted[j]>=v
            while (less(unsorted[++i], v)) if (i == hi) break;

            //此内循环结束时unsorted[j]<=v
            while (less(v, unsorted[--j])) if (j == lo) break;

            if (i >= j) break;
            exchange(unsorted, i, j);
        }

        //将v和unsorted[j]互换,保证unsorted[lo...(j-1)]均小于等于v,unsorted[(j+1)...hi]大于等于v
        exchange(unsorted, lo, j);

        return j;
    }
}

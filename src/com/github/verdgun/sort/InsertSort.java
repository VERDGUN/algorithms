package com.github.verdgun.sort;

/**
 * 插入排序(升序排序)
 */
public class InsertSort<T extends Comparable<T>> implements ISort<T> {
    @Override
    public T[] sort(T[] unsorted) {
        return sort(unsorted, 0, unsorted.length - 1);
    }

    public T[] sort(T[] unsorted, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            T current = unsorted[i];
            int j = i;
            for (; j > 0; j--) {
                //如果current大于unsorted[j - 1],说明current应该排在j-1之后,即j的位置
                if (!lessThen(current, unsorted[j - 1]))
                    break;
                unsorted[j] = unsorted[j - 1];
            }
            unsorted[j] = current;
        }
        return unsorted;
    }
}

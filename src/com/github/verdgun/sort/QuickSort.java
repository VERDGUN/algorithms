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

    /**
     * 将数组中lo...hi中的元素进行分区（按照第一个元素为标准）
     *
     * @param unsorted 待排序数组
     * @param lo       数组起始下标
     * @param hi       数组结束下标
     * @return 第一个元素在分区后的数组中的位置
     */
    private int partition(T[] unsorted, int lo, int hi) {
        T current = unsorted[lo];
        int lessIndex = lo, greateIndex = hi + 1;
        //循环结束时unsorted[lo+1...greateIndex]均小于等于current,unsorted[(greateIndex+1),hi]均大于等于v.
        while (true) {
            //此内循环结束时unsorted[lessIndex]>=current
            while (lessThen(unsorted[++lessIndex], current)) if (lessIndex == hi) break;

            //此内循环结束时unsorted[greateIndex]<=current
            while (lessThen(current, unsorted[--greateIndex])) if (greateIndex == lo) break;

            if (lessIndex >= greateIndex) break;
            exchange(unsorted, lessIndex, greateIndex);
        }

        //将v和unsorted[greateIndex]互换,保证unsorted[lo...(greateIndex-1)]均小于等于v,unsorted[(greateIndex+1)...hi]大于等于v
        exchange(unsorted, lo, greateIndex);

        return greateIndex;
    }
}

package com.github.verdgun.sort;

public class HeapSort implements ISort {
    @Override
    public Comparable[] sort(Comparable[] unsorted) {
        int length = unsorted.length;
        //堆初始化(大根堆)
        for (int i = length / 2; i >= 1; i--) {
            sink(unsorted, i, length);
        }

        for (int i = length; i > 0; ) {
            exch(unsorted, 1, i--);
            sink(unsorted, 1, i);
        }
        return unsorted;
    }

    public boolean less(Comparable[] unsorted, int i, int j) {
        return less(unsorted[i - 1], unsorted[j - 1]);
    }

    @Override
    public void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = temp;
    }

    /**
     * 按小根堆排序
     *
     * @param unsorted 待排序数组
     * @param i        当前节点
     * @param end      末尾节点
     */
    private void sink(Comparable[] unsorted, int i, int end) {
        int child = 2 * i;
        while (child <= end) {
            //取出
            if (child < end && less(unsorted, child, child + 1)) child++;
            if (!less(i, child)) break;
            exch(unsorted, i, child);
            child *= 2;
        }
    }

}

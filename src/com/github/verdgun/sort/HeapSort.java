package com.github.verdgun.sort;

public class HeapSort<T extends Comparable<T>> implements ISort<T> {
    @Override
    public T[] sort(T[] unsorted) {
        int length = unsorted.length;
        //堆初始化(大根堆)
        for (int i = length / 2; i > 0; i--) {
            sink(unsorted, i, length);
        }

        for (int i = length; i > 1; ) {
            exchange(unsorted, 1, i--);
            sink(unsorted, 1, i);
        }
        return unsorted;
    }

    public boolean less(T[] unsorted, int i, int j) {
        return less(unsorted[i - 1], unsorted[j - 1]);
    }

    @Override
    public void exchange(T[] a, int i, int j) {
        T temp = a[i - 1];
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
    private void sink(T[] unsorted, int i, int end) {
        while (2 * i <= end) {
            int child = 2 * i;
            //取出
            if (child < end && less(unsorted, child, child + 1)) child++;
            if (!less(unsorted, i, child)) break;
            exchange(unsorted, i, child);
            i = child;
        }
    }

}

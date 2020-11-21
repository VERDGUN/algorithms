package com.github.verdgun.sort;

/**
 * 归并排序
 */
public class MergeSort<T extends Comparable<T>> implements ISort<T> {
    /**
     * 自顶向下归并排序
     *
     * @param unsorted 待排序数组
     * @return 已排序数组
     */
    @Override
    public T[] sort(T[] unsorted) {
        Object[] temp = new Object[unsorted.length];
        return sort(unsorted, 0, unsorted.length - 1, temp);
    }

    private T[] sort(T[] unsorted, int lo, int hi, Object[] temp) {
        if (hi <= lo) return unsorted;
        //优化2.2.2.1: 如果数组长度小于17,则直接用插入排序
        if ((hi - lo + 1) < 17) {
            return (new InsertSort<T>()).sort(unsorted);
        }

        int mid = lo + (hi - lo) / 2;

        sort(unsorted, lo, mid, temp);
        sort(unsorted, mid + 1, hi, temp);

        //优化2.2.2.2: 数组unsorted[lo...mid],unsorted[mid+1...hi]已经排好序,如果unsorted[mid]<unsorted[mid+1],说明unsorted[lo...mid]中的任意一个元素都小于unsorted[mid+1...hi]中的任意一个元素
        //也就说明数组unsorted[lo...hi]已经是有序数组,不需要合并操作.
        if (lessThen(unsorted[mid], unsorted[mid + 1]))
            return unsorted;
        else merge(unsorted, lo, mid, hi, temp);

        return unsorted;
    }

    protected void merge(T[] sorted, int lo, int mid, int hi, Object[] temp) {
        int i = lo, j = lo, k = mid + 1;
        for (; i <= hi; i++) {
            //j > mid说明sorted[lo...mid]中的元素已经排好序，直接将sorted[mid+1...hi]剩余的元素放入数组中即可
            if (j > mid) {
                temp[i] = sorted[k++];
            }
            //k > hi说明sorted[mid+1...hi]中的元素已经排好序，直接将sorted[lo...mid]剩余的元素放入数组中即可
            else if (k > hi) {
                temp[i] = sorted[j++];
            } else if (lessThen(sorted[j], sorted[k])) {
                temp[i] = sorted[j++];
            } else {
                temp[i] = sorted[k++];
            }
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(temp, lo, sorted, lo, hi - lo + 1);
    }
}

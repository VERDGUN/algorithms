package com.github.verdgun.sort;

/**
 * 归并排序
 */
public class MergeSort implements ISort {
    /**
     * 自顶向下归并排序
     *
     * @param unsorted 待排序数组
     * @return 已排序数组
     */
    @Override
    public Comparable[] sort(Comparable[] unsorted) {
        return sort(unsorted, 0, unsorted.length - 1);
    }

    private Comparable[] sort(Comparable[] unsorted, int lo, int hi) {
        if (hi <= lo) return unsorted;
        //优化2.2.2.1: 如果数组长度小于17,则直接用插入排序
        if ((hi - lo + 1) < 17) {
            return (new InsertSort()).sort(unsorted);
        }

        int mid = lo + (hi - lo) / 2;

        sort(unsorted, lo, mid);
        sort(unsorted, mid + 1, hi);

        //优化2.2.2.2: 数组unsorted[lo...mid],unsorted[mid+1...hi]已经排好序,如果unsorted[mid]<unsorted[mid+1],说明unsorted[lo...mid]中的任意一个元素都小于unsorted[mid+1...hi]中的任意一个元素
        //也就说明数组unsorted[lo...hi]已经是有序数组,不需要合并操作.
        if (unsorted[mid].compareTo(unsorted[mid + 1]) < 0)
            return unsorted;
        else merge(unsorted, lo, mid, hi);

        return unsorted;
    }

    protected void merge(Comparable[] sorted, int lo, int mid, int hi) {
        Comparable[] temp = new Comparable[hi - lo + 1];
        int k = 0, i = lo, j = mid + 1;
        for (; k < temp.length; k++) {
            if (i > mid) {
                temp[k] = sorted[j++];
            } else if (j > hi) {
                temp[k] = sorted[i++];
            } else if (less(sorted[i], sorted[j])) {
                temp[k] = sorted[i++];
            } else {
                temp[k] = sorted[j++];
            }
        }

        System.arraycopy(temp, 0, sorted, lo, k);
    }
}

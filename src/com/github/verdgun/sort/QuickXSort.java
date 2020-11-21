package com.github.verdgun.sort;

/**
 * 快速排序(优化版)
 */
public class QuickXSort<T extends Comparable<T>> implements ISort<T> {
    @Override
    public T[] sort(T[] unsorted) {
        return sort(unsorted, 0, unsorted.length - 1);
    }

    private T[] sort(T[] unsorted, int lo, int hi) {
        if (lo >= hi) {
            return unsorted;
        }

        //优化2.3.3.1如果数组长度过小,则直接使用插入排序
        if ((hi - lo + 1) < INSERTION_SORT_CUTOFF) {
            return (new InsertSort<T>()).sort(unsorted, lo, hi);
        }

        int j = partition(unsorted, lo, hi);
        sort(unsorted, lo, j - 1);
        sort(unsorted, j + 1, hi);

        return unsorted;
    }

    private int partition(T[] unsorted, int lo, int hi) {
        //优化: 取中位数,并将该值作为切分元素
        int m = median3(unsorted, lo, hi);
        exchange(unsorted, lo, m);


        int lessIndex = lo;
        int greatIndex = hi + 1;
        T v = unsorted[lo];
        //找到第一个大于等于v的元素
        while (lessThen(unsorted[++lessIndex], v)) {
            //如果最后一个元素依然小于v,说明unsorted[(lo+1)...hi]都小于v,交换unsorted[lo]和unsorted[hi]之后,直接返回切分后的元素下标hi
            if (lessIndex == hi) {
                exchange(unsorted, lessIndex, lo);
                return lessIndex;
            }
        }

        ////找到第一个小于等于v的元素
        while (lessThen(v, unsorted[--greatIndex])) {
            //如果unsorted[lo + 1]依然大于v(unsorted[lo]),则说明unsorted[(lo+1)...hi]都大于v,直接返回切分后的元素下标lo
            if (greatIndex == lo + 1) {
                return lo;
            }
        }

        //如果lessIndex>=greatIndex,说明元素切分结束
        while (lessIndex < greatIndex) {
            //执行交换后,unsorted[lessIndex] <= v <= unsorted[greatIndex],且此时lessIndex < greatIndex
            exchange(unsorted, lessIndex, greatIndex);
            //因此,此内循环不会发生数组下标越界(因为必然存在unsorted[greatIndex] >= v,从而使得循环终结)
            while (lessThen(unsorted[++lessIndex], v)) ;
            while (lessThen(v, unsorted[--greatIndex])) ;
        }

        exchange(unsorted, greatIndex, lo);
        return greatIndex;
    }

    private int median3(T[] unsorted, int lo, int hi) {
        int midIndex = lo + (hi - lo + 1) / 2;

        int min = lessThen(unsorted[lo], unsorted[midIndex]) ? lo : midIndex;
        min = lessThen(unsorted[min], unsorted[hi]) ? min : hi;
        return min;
    }
}

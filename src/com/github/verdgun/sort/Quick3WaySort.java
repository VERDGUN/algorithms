package com.github.verdgun.sort;

public class Quick3WaySort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public T[] sort(T[] unsorted) {
        return sort(unsorted, 0, unsorted.length - 1);
    }

    private T[] sort(T[] unsorted, int lo, int hi) {
        if (lo >= hi) return unsorted;
        T v = unsorted[lo];
        int lt = lo, gt = hi, i = lo + 1;
        while (i <= gt) {
            int compareTo = unsorted[i].compareTo(v);
            // 如果小于v,则将元素unsorted[i]和数组左侧的unsorted[lt]交换
            // 因为大于v的元素会移到右侧子数组unsorted[gt...hi]中,因此unsorted[i]和unsorted[lt]交换后依然能保证unsorted[lo,lt]小于等于v
            // 所以将lt,i指向下一个元素即可,不需要再次比较unsorted[i]和v的关系
            if (compareTo < 0) {
                exchange(unsorted, lt++, i++);
            }
            // 如果大于v,则将元素unsorted[i]和数组右侧的unsorted[gt]交换
            // 因为元素unsorted[gt]没有和v比较过,需要再次比较unsorted[i]和v的关系
            // 所以此处i不能指向下一个元素
            else if (compareTo > 0) {
                exchange(unsorted, i, gt--);
            }
            // 如果unsorted[i]等于v,则不交换元素
            else i++;
        }
        //循环结束后,存在unsorted[lo...(lt-1)] < v = unsorted[lt...gt] < unsorted[gt+1...hi]

        sort(unsorted, lo, lt - 1);
        sort(unsorted, gt + 1, hi);

        return unsorted;
    }
}

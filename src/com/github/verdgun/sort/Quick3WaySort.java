package com.github.verdgun.sort;

public class Quick3WaySort<T extends Comparable<T>> implements ISort<T> {

    @Override
    public T[] sort(T[] unsorted) {
        return sort(unsorted, 0, unsorted.length - 1);
    }

    private T[] sort(T[] unsorted, int lo, int hi) {
        if (lo >= hi) return unsorted;
        T current = unsorted[lo];
        int lessIndex = lo;
        int greateIndex = hi;
        int index = lo + 1;
        while (index <= greateIndex) {
            int compareTo = unsorted[index].compareTo(current);
            // 如果小于current,则将元素unsorted[index]和数组左侧的unsorted[lessIndex]交换
            // 因为大于current的元素会移到右侧子数组unsorted[greateIndex...hi]中,因此unsorted[index]和unsorted[lessIndex]交换后依然能保证unsorted[lo,lessIndex]小于等于v
            // 所以将lessIndex,index指向下一个元素即可,不需要再次比较unsorted[index]和current的关系
            if (compareTo < 0) {
                exchange(unsorted, lessIndex++, index++);
            }
            // 如果大于v,则将元素unsorted[index]和数组右侧的unsorted[greateIndex]交换
            // 因为元素unsorted[greateIndex]没有和current比较过,需要再次比较unsorted[index]和current的关系
            // 所以此处i不能指向下一个元素
            else if (compareTo > 0) {
                exchange(unsorted, index, greateIndex--);
            }
            // 如果unsorted[index]等于v,则不交换元素
            else index++;
        }
        //循环结束后,存在unsorted[lo...(lessIndex-1)] < current = unsorted[lessIndex...greateIndex] < unsorted[greateIndex+1...hi]

        sort(unsorted, lo, lessIndex - 1);
        sort(unsorted, greateIndex + 1, hi);

        return unsorted;
    }
}

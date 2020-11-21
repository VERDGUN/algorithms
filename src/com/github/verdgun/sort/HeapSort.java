package com.github.verdgun.sort;

/**
 * 堆排序
 */
public class HeapSort<T extends Comparable<T>> implements ISort<T> {
    @Override
    public T[] sort(T[] unsorted) {
        int length = unsorted.length;
        //堆初始化(大根堆),ceil(length / 2)为最后一个非叶子节点
        for (int i = length / 2; i > 0; i--) {
            sink(unsorted, i, length);
        }

        //经过上一个for循环排序，根节点为最大元素
        //将根元素和最后的一个元素交换位置，并将其从堆中移除，再重新排序为大根堆
        for (int index = length; index > 1; --index) {
            exchange(unsorted, 0, index - 1);
            sink(unsorted, 1, index - 1);
        }
        return unsorted;
    }

    /**
     * 将当前节点下沉到具体位置
     *
     * @param unsorted 待排序数组
     * @param i        当前节点
     * @param end      末尾节点
     */
    private void sink(T[] unsorted, int i, int end) {
        while (2 * i <= end) {
            //2i为做当前节点的左子节点
            int child = 2 * i;
            //取出  (child < end)--防止超出节点范围，左子节点在数组中的位置为child-1，右子节点在数组中的位置为child+1-1=child
            //如果左子节点小于右子节点，则另child指向右子节点
            if (child < end && lessThen(unsorted[child - 1], unsorted[child])) child++;


            //如果当前节点大于最大的子节点，则结束排序,否则交换元素exchange(unsorted, i, child)，并递归下去
            if (!lessThen(unsorted[i - 1], unsorted[child - 1])) break;
            exchange(unsorted, i - 1, child - 1);
            i = child;
        }
    }

}

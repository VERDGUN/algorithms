package com.github.verdgun.queue;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @param <Item> 元素类型
 */
public class IndexMinPQ<Item extends Comparable<Item>> {


    /**
     * 堆有序(小根序)的优先队列,其元素为Item实例的索引(即index和keys数组的索引)
     */
    private int[] priorityQueue;

    /**
     * 索引,值为Item实例在优先队列中的位置(即priorityQueue数组的索引)
     */
    private int[] indexes;

    /**
     * 保存索引对应的Item实例
     */
    private Item[] keys;
    /**
     * 索引队列的最大长度,指明了最多能保存多少个元素
     */
    private int maxLength;

    /**
     * 优先队列的长度
     */
    private int priorityQueueLength = 0;

    public IndexMinPQ(int maxLength, Class<Item> klass) {
        this.maxLength = maxLength;
        this.priorityQueue = new int[maxLength + 1];
        this.indexes = new int[maxLength];
        this.keys = (Item[]) Array.newInstance(klass, maxLength);

        Arrays.fill(indexes, -1);
    }

    public void insert(int index, Item item) {
        validateIndex(index);
        if (constains(index)) throw new IllegalArgumentException("index is already in the priority queue");
        priorityQueueLength++;
        priorityQueue[priorityQueueLength] = index;
        indexes[index] = priorityQueueLength;
        keys[index] = item;
        swim(priorityQueueLength);
    }

    private void swim(int i) {
        while (i > 1 && greater(i / 2, i)) {
            exchange(i, i / 2);
            i /= 2;
        }
    }

    private void exchange(int i, int k) {
        int tempIndex = priorityQueue[i];
        priorityQueue[i] = priorityQueue[k];
        priorityQueue[k] = tempIndex;
        indexes[priorityQueue[i]] = i;
        indexes[priorityQueue[k]] = k;
    }

    private boolean greater(int i, int i1) {
        return keys[priorityQueue[i]].compareTo(keys[priorityQueue[i1]]) > 0;
    }

    private void validateIndex(int index) {
        if (index < 0) throw new IllegalArgumentException("index is negative: " + index);
        if (index >= maxLength) throw new IllegalArgumentException("index >= capacity: " + index);
    }

    public void change(int index, Item key) {
        if (!constains(index)) throw new NoSuchElementException("index is not in the priority queue");
        int index1 = indexes[index];
        keys[index] = key;
        swim(index1);
        sink(index1);
    }

    private void sink(int i) {
        while (2 * i <= priorityQueueLength) {
            int child = 2 * i;
            if (child < priorityQueueLength && greater(child, child + 1)) child += 1;
            if (!greater(i, child)) break;
            exchange(i, child);
            i = child;
        }
    }

    public boolean constains(int index) {
        validateIndex(index);
        return indexes[index] != -1;
    }


    public boolean isEmpty() {
        return priorityQueueLength == 0;
    }

    public int delMin() {
        if (priorityQueueLength == 0) throw new NoSuchElementException("Priority queue underflow");
        int index = priorityQueue[1];
        indexes[index] = -1;
        keys[index] = null;
        exchange(1, priorityQueueLength--);
        sink(1);
        return index;
    }
}

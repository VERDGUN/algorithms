package com.github.verdgun.queue;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @param <K> 元素类型
 */
public class IndexMinPQ<K extends Comparable<K>> {


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
    private K[] keys;
    /**
     * 索引队列的最大长度,指明了最多能保存多少个元素
     */
    private int maxLength;
    private int size;
    /**
     * 优先队列的长度
     */
    private int priorityQueueLength;

    public IndexMinPQ(int maxLength, Class<K> klass) {
        this.maxLength = maxLength;
        this.priorityQueue = new int[maxLength + 1];
        this.indexes = new int[maxLength];
        this.keys = (K[]) Array.newInstance(klass, maxLength);

        Arrays.fill(indexes, -1);
    }

    public int put(K item) {
        insert(++size, item);
        return size;
    }

    private void insert(int index, K item) {
        validateIndex(index);
        if (constains(index)) throw new IllegalArgumentException("index is already in the priority queue");
        //存值
        keys[index] = item;

        //优先队列不直接存值，而是存keys数组的下标
        priorityQueue[++priorityQueueLength] = index;
        indexes[index] = priorityQueueLength;
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

    private boolean greater(int o, int other) {
        return keys[priorityQueue[o]].compareTo(keys[priorityQueue[other]]) > 0;
    }

    private void validateIndex(int index) {
        if (index < 0) throw new IllegalArgumentException("index is negative: " + index);
        if (index >= maxLength) throw new IllegalArgumentException("index >= capacity: " + index);
    }

    public void change(int index, K key) {
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

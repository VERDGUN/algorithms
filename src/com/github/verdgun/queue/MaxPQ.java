package com.github.verdgun.queue;

import java.util.Comparator;
import java.util.Iterator;

public class MaxPQ<K> implements Iterable<K> {
    private Object[] pq;
    private int size = 0;
    private Comparator<K> comparator;

    private MaxPQ() {
    }

    public MaxPQ(int maxN, Comparator<K> comparator) {
        pq = new Object[maxN + 1];
        this.comparator = comparator;
    }

    public void insert(K v) {
        pq[++size] = v;
        swim(size);
    }

    /**
     * 将索引所在的元素上浮
     *
     * @param n 当前需要上浮的元素的索引
     */
    private void swim(int n) {
        while (n > 1 && less(n / 2, n)) {
            exchange(n / 2, n);

            n /= 2;
        }
    }

    private void exchange(int k, int k1) {
        Object temp = pq[k];
        pq[k] = pq[k1];
        pq[k1] = temp;
    }

    private boolean less(int k, int k1) {
        return comparator.compare((K) pq[k], (K) pq[k1]) < 0;
    }

    public K max() {
        if (size == 0) {
            return null;
        }

        return (K) pq[1];
    }

    private void sink(int i) {
        //i的子节点为2i和2i+1,只有2i<=size才能保证i存在子节点(当2i==length时,i只有一个左子节点)
        while (2 * i <= size) {
            //先让child指向i的左子节点
            int child = 2 * i;
            //如果2i<size,说明存在右子节点,需要比较左右子节点的大小,并获取最大的那个
            if (child < size && less(child, child + 1)) child++;

            // 如果i大于等于它的子节点(最大的那个子节点),则结束比较
            if (!less(i, child)) break;

            exchange(i, child);

            i = child;
        }
    }

    public K delMax() {
        Object temp = pq[1];
        exchange(1, size);
        pq[size--] = null;
        sink(1);

        return (K) temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int next = 0;

            @Override
            public boolean hasNext() {
                return next < pq.length;
            }

            @Override
            public K next() {
                return (K) pq[next++];
            }
        };
    }
}

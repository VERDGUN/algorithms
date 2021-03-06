package com.github.verdgun.sort;

import static java.lang.System.*;

/**
 * 排序算法接口
 *
 * @param <T> 可比较类型
 */
public interface ISort<T extends Comparable<T>> {
    int INSERTION_SORT_CUTOFF = 8;

    T[] sort(T[] unsorted);

    default boolean lessThen(T v, T w) {
        return v.compareTo(w) < 0;
    }

    default void exchange(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    default void show(T[] a) {
        out.println("array ");
        for (T T : a) {
            out.printf("%s \t", T);
        }
    }

    default boolean isSorted(T[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (lessThen(a[i], a[i + 1])) {
                return false;
            }
        }
        return true;
    }

}

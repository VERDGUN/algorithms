package com.github.verdgun.sort;

public interface ISort<T extends Comparable> {
    T[] sort(T[] unsorted);

    default boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    default void exch(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    default void show(T[] a) {
        System.out.println("array ");
        for (T T : a) {
            System.out.printf("%s \t", T);
        }
    }

    default boolean isSorted(T[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (less(a[i], a[i + 1])) {
                return false;
            }
        }
        return true;
    }

}

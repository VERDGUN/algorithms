package com.github.verdgun;

import java.util.Random;

public class TestUtil {
    public static Integer[] init(int length) {
        Integer[] a = new Integer[length];
        final Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(1000);
        }
        return a;
    }
}

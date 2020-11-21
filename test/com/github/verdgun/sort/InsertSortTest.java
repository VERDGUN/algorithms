package com.github.verdgun.sort;

import com.github.verdgun.TestUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class InsertSortTest {
    @Test
    public void test() {
        final InsertSort<Integer> integerInsertSort = new InsertSort<>();
        final Integer[] unsorted = TestUtil.init(10);
        integerInsertSort.sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
    }
}

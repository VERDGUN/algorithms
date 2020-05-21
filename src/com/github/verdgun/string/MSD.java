package com.github.verdgun.string;

import com.github.verdgun.sort.InsertSort;

public class MSD {
    private static final int R = 256;
    private static final int M = 15;

    private static int charAt(String s, int d) {
        if (d < s.length()) s.charAt(d);
        return -1;
    }

    public static void sort(String[] a) {
        int length = a.length;
        String[] aux = new String[length];
        sort(a, 0, length - 1, 0, aux);

    }

    private static void sort(String[] a, int lo, int hi, int d, String[] aux) {
        /*if(hi<=lo+M){
            InsertSort insertSort = new InsertSort();
            insertSort.sort(a);
            return;
        }*/
        int[] count = new int[R + 2];
        //计算频率
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }

        //计算索引
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        //数据分类,由于只对a[lo,hi]排序(一共hi-lo+1个元素),因此aux数组前面hi-lo+1个元素会被用到
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        System.arraycopy(aux, 0, a, lo, hi - lo + 1);

        //递归地以每个字符为键进行排序
        for (int i = 0; i < R; i++) {
            sort(a, lo + count[i], lo + count[i + 1] - 1, d + 1, aux);
        }
        
    }
}

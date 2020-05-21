package com.github.verdgun.string;

public class LSD {
    private static final int R = 256;

    public void sort(String[] a, int w) {
        int length = a.length;
        String[] aux = new String[length];
        for (int d = w - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            //计算频率, count[i+1]保存的是i出现的频率
            for (String s : a) {
                count[s.charAt(d) + 1]++;
            }

            //将频率转换为索引,转换之后count[i]保存的是i出现的索引(第一个i)
            for (int i = 0; i < R; i++) {
                count[i + 1] += count[i];
            }

            for (int i = 0; i < length; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            //回写
            System.arraycopy(aux, 0, a, 0, length);
        }
    }
}

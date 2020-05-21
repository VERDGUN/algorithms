package com.github.verdgun.string;

import java.util.Arrays;

public class BoyerMoore {
    private int[] right;
    private String pattern;
    private static final int R = 256;

    public BoyerMoore(String pattern) {
        this.pattern = pattern;
        int length = pattern.length();
        right = new int[R];
        Arrays.fill(right, -1);
        for (int i = 0; i < length; i++) {
            right[pattern.charAt(i)] = i;
        }
    }

    public int search(String txt) {
        int length = pattern.length();
        int txtLenght = txt.length();
        int skip;
        for (int i = 0; i <= txtLenght - length; i += skip) {
            skip = 0;
            for (int j = length - 1; j >= 0; j--) {
                char c = txt.charAt(i + j);
                if (c != pattern.charAt(j)) {
                    skip = j - right[c];
                    //如果skip=0,则向右移动1个位置
                    if (skip < 1) skip = 1;
                    break;
                }
            }
            if (skip == 0) {
                return i;
            }
        }

        return txtLenght;
    }
}

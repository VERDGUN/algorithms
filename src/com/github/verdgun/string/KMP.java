package com.github.verdgun.string;

public class KMP {
    private String pattern;
    private int[][] dfa;
    private static final int R = 256;

    public KMP(String pattern) {
        this.pattern = pattern;
        int length = pattern.length();
        dfa = new int[R][length];
        dfa[pattern.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < length; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][x];
            }

            dfa[pattern.charAt(j)][j] = j + 1;
            x = dfa[pattern.charAt(j)][x];
        }
    }

    public int search(String txt) {
        int i = 0, j = 0;
        for (; i < txt.length() && j < pattern.length(); i++) {
            j = dfa[txt.charAt(i)][j];
        }

        if (j == pattern.length()) {
            return i - pattern.length();
        } else {
            return txt.length();

        }
    }
}

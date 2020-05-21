package com.github.verdgun.string;

public class RabinKarp {
    private String pattern;
    private long patternHash;
    private long Q = 997;
    private int R = 256;
    private long RM;//R^(M-1)%Q

    public RabinKarp(String pattern) {
        this.pattern = pattern;
        RM = 1;
        for (int i = 1; i < pattern.length(); i++) {
            RM = (R * RM) % Q;
        }
        patternHash = hash(pattern, pattern.length());
    }

    public int search(String txt) {
        int length = txt.length();
        int patLength = pattern.length();
        long txtHash = hash(txt, patLength);
        if (patternHash == txtHash && check(0)) {
            return 0;
        }
        for (int i = patLength; i < length; i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i - patLength) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patternHash == txtHash) {
                if (check(i - patLength + 1)) {
                    return i - patLength + 1;
                }
            }
        }

        return length;
    }

    private boolean check(int i) {
        return true;
    }

    private long hash(String pattern, int length) {
        long h = 0;
        for (int i = 0; i < length; i++) {
            h = (R * h + pattern.charAt(i)) % Q;
        }

        return h;
    }
}

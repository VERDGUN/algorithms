package com.github.verdgun.string;

//import edu.princeton.cs.algs4.NFA;

public class NFATest {
    public static void main(String[] args) {
        String re = "(A*B|AC*)D";
        NFA nfa = new NFA(re);
        String[] in = {
                "AC",
                "AD",
                "AAA",
                "ABD",
                "ADD",
                "BCD",
                "BD",
                "ABCCBD",
                "BABAAA",
                "BABBAAA"
        };

        for (String s : in) {
            if (nfa.recognizes(s)) {
                System.out.println(s);
            }
        }
    }
}

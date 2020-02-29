package com.github.verdgun;

import com.github.verdgun.tree.BST;

public class BSTTest {
    public static void main(String[] args) {
        String[] strings = {  "c", "b", "w", "e", "f", "z", "x", "v", "s", "a"};
        BST<String, String> bst = new BST<>();
        edu.princeton.cs.algs4.BST<Integer,String> algbst = new edu.princeton.cs.algs4.BST<>();
        for (int i = 0; i < strings.length; i++) {
            bst.put(strings[i], strings[i]);
            algbst.put(i,strings[i]);
        }

        System.out.println(bst.rank("f"));
        System.out.println(algbst.select(4));
        bst.delete("f");
        System.out.println(bst);
    }
}

package com.github.verdgun.tree;

import lombok.Data;

/**
 * 树节点
 *
 * @param <Key>   键
 * @param <Value> 值
 */
@Data
public class Node<Key extends Comparable, Value> {
    private Node<Key, Value> previous, left, right;
    private int size;
    private Key key;
    private Value value;

    public Node(int size, Key key, Value value) {
        this.size = size;
        this.key = key;
        this.value = value;
    }
}

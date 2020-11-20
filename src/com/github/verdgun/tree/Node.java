package com.github.verdgun.tree;

import lombok.Data;

/**
 * 树节点
 *
 * @param <K> 键
 * @param <V> 值
 */
@Data
public class Node<K extends Comparable<K>, V> {
    private Node<K, V> parent;
    private Node<K, V> left;
    private Node<K, V> right;
    private int size;
    private K key;
    private V value;

    public Node(int size, K key, V value) {
        this.size = size;
        this.key = key;
        this.value = value;
    }
}

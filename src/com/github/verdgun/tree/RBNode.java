package com.github.verdgun.tree;

import lombok.Builder;
import lombok.Data;

/**
 * RedBlack Nodd 红黑树的节点
 */
@Data
@Builder
public class RBNode<K extends Comparable<K>, V> {
    private K key;
    private V value;
    /*
     * (其父节点)指向该节点的链接的颜色
     */
    private boolean red;
    private RBNode<K, V> left;
    private RBNode<K, V> right;
    private int size;
}

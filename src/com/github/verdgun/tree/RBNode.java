package com.github.verdgun.tree;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * RedBlack Nodd 红黑树的节点
 */
@Data
@Builder
public class RBNode<Key extends Comparable<Key>, Value> {
    private Key key;
    private Value value;
    /*
     * (其父节点)指向该节点的链接的颜色
     */
    private boolean red;
    private RBNode<Key, Value> left;
    private RBNode<Key, Value> right;
    private int size;
}

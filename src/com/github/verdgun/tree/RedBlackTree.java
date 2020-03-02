package com.github.verdgun.tree;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private RBNode<Key, Value> root;

    public int size() {
        return root.getSize();
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.setRed(false);
    }

    private RBNode<Key, Value> put(RBNode<Key, Value> root, Key key, Value value) {
        if (root == null) {
            return RBNode.<Key, Value>builder().key(key).value(value).red(true).size(1).build();
        }

        int compare = key.compareTo(root.getKey());
        if (compare == 0) {
            root.setValue(value);
        } else if (compare < 0) {
            RBNode<Key, Value> node = put(root.getLeft(), key, value);
            root.setLeft(node);
        } else {
            RBNode<Key, Value> node = put(root.getRight(), key, value);
            root.setRight(node);
        }

        //进行调整
        //步骤一: 如果右子链接是红链接而左子链接是黑链接,则进行左旋
        if (isRed(root.getRight()) && !isRed(root.getLeft())) {
            root = rotateLeft(root);
        }

        //步骤二: 如果左子链接和左子链接的左子链接都是红链接,则进行右旋
        if (isRed(root.getLeft()) && isRed(root.getLeft().getLeft())) {
            root = rotateRight(root);
        }

        //步骤三: 如果左、右子链接都是红链接
        if (isRed(root.getLeft()) && isRed(root.getRight())) {
            root.setRed(true);
            root.getLeft().setRed(false);
            root.getRight().setRed(false);
        }

        return root;
    }

    /**
     * 左旋转
     *
     * @param root 父结点
     * @return 左旋后的父结点
     */
    private RBNode<Key, Value> rotateLeft(RBNode<Key, Value> root) {
        RBNode<Key, Value> right = root.getRight();
        //交换父结点(root)和右子结点的连接,使右子结点成为父结点
        root.setRight(right.getLeft());
        right.setLeft(root);

        //重新设置大小
        right.setSize(root.getSize());
        root.setSize(1 + root.getLeft().getSize() + root.getRight().getSize());
        //设置红黑链接
        right.setRed(root.isRed());
        root.setRed(true);

        return right;
    }

    /**
     * 右旋转
     *
     * @param root 父结点
     * @return 右旋后的父结点
     */
    private RBNode<Key, Value> rotateRight(RBNode<Key, Value> root) {
        RBNode<Key, Value> left = root.getLeft();

        root.setLeft(left.getRight());
        left.setRight(root);

        //
        left.setSize(root.getSize());
        root.setSize(1 + root.getLeft().getSize() + root.getRight().getSize());

        left.setRed(root.isRed());
        root.setRed(true);

        return left;
    }

    private boolean isRed(RBNode<Key, Value> node) {
        return node != null ? node.isRed() : false;
    }
}

package com.github.verdgun.tree;

import javax.swing.tree.TreeNode;
import java.util.NoSuchElementException;

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
            flipColor(root);
        }

        return root;
    }

    /**
     * 反正当前节点和其左右连接节点的颜色
     *
     * @param node 节点
     */
    private void flipColor(RBNode<Key, Value> node) {
        node.setRed(!node.isRed());
        node.getLeft().setRed(!node.getLeft().isRed());
        node.getRight().setRed(!node.getRight().isRed());
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

    public void deleteMin() {
        //如果根节点、左子结点、右子节点都是2-节点，将根节点主动标记为红节点,方便调用deleteMin(root)时调用moveRedLeft()方法
        if (!isRed(root.getLeft()) && !isRed(root.getRight())) {
            root.setRed(true);
        }

        root = deleteMin(root);

        //此时根节点节点颜色可能是red,需要改为black
        if (root != null) {
            root.setRed(false);
        }
    }

    private RBNode<Key, Value> deleteMin(RBNode<Key, Value> node) {
        //此处实现真正的删除操作,即当该节点没有左子结点时,它就是最小的那个节点
        if (node.getLeft() == null) {
            return null;
        }

        //如果node为根节点,且根节点、左子结点、右子节点都是2-节点，则将3个节点合并为一个3-节点
        //或者node节点的情况满足条件3:当前节点的左子结点和他的亲兄弟节点都是2-节点,则将左子结点、父节点的最小键、右子节点合并
        //总结:向下递归删除过程中(调用deleteMin()方法),会保证当前节点必定是红节点(对于根节点,需要主动标记为红节点)
        if (!isRed(node.getLeft()) && !isRed(node.getRight())) {
            //判断是否需要将当前节点及其左右节点合并为一个3-节点
            node = moveRedLeft(node);
        }

        node.setLeft(deleteMin(node.getLeft()));

        //删除完成后,进行红黑树平衡,保证去除上述递归过程中产生的临时4-节点
        return balance(node);
    }

    private RBNode<Key, Value> balance(RBNode<Key, Value> node) {
        if (node.getRight().isRed()) {
            node = rotateLeft(node);
        }

        if (isRed(node.getRight()) && !isRed(node.getLeft())) {
            node = rotateLeft(node);
        }

        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }

        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            flipColor(node);
        }

        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
        return node;
    }

    private RBNode<Key, Value> moveRedLeft(RBNode<Key, Value> node) {
        //反转颜色,由于${!isRed(node.getLeft()) && !isRed(node.getRight())}=true,转换后node、node.left、node.right组成一个3-节点
        flipColor(node);
        //如果node的右子节点的左子结点是红节点,说明node的右子节点不是最小值,需要进行右旋变换
        if (isRed(node.getRight().getLeft())) {
            node.setRight(rotateRight(node.getRight()));
            //右旋变换之后,需要对node节点继续左旋变换,使得node、node.left、node.right组成一个3-节点
            node = rotateLeft(node);
            //是的原来的node和原来node节点的左子结点组成非2-节点,而原来node的右子节点成为他们的父结点
            flipColor(node);
        }

        return node;
    }

    public void deleteMax() {
        if (size() == 0) {
            throw new NoSuchElementException("BST underflow");
        }

        if (!isRed(root.getLeft()) && !isRed(root.getRight())) {
            root.setRed(true);
        }

        root = deleteMax(root);

        if (!isEmpty()) {
            root.setRed(false);
        }
    }

    private RBNode<Key, Value> deleteMax(RBNode<Key, Value> root) {
        if (root.getRight() == null) {
            return null;
        }

        if (!isRed(root.getLeft()) && !isRed(root.getRight())) {
            root = moveRedRight(root);
        }

        root.setRight(deleteMax(root.getRight()));

        return balance(root);
    }

    private RBNode<Key, Value> moveRedRight(RBNode<Key, Value> node) {
        flipColor(node);
        if (isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
            flipColor(node);
        }

        return node;
    }

    private boolean isEmpty() {
        return root == null;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.getLeft()) && !isRed(root.getRight()))
            root.setRed(true);
        delete(root, key);
    }

    private RBNode<Key, Value> delete(RBNode<Key, Value> root, Key key) {
        if (key.compareTo(root.getKey()) < 0) {
            if (!isRed(root.getLeft()) && !isRed(root.getRight()))
                root = moveRedLeft(root);

            root.setLeft(delete(root.getLeft(), key));
        } else {
            //如果左子节点是红节点,则先右旋
            if (isRed(root.getLeft())) {
                root = rotateRight(root);
            }
            //如果当前节点就是需要删除的节点,且其右子节点为空,则删除它
            if ((key.compareTo(root.getKey()) == 0) && (root.getRight() == null)) {
                return null;
            }

            //如果存在右节点,则将当前节点和右侧节点变为非2-节点
            if (!isRed(root.getRight()) && !isRed(root.getRight().getLeft())) {
                root = moveRedRight(root);
            }

            if (key.compareTo(root.getKey()) == 0) {
                RBNode temp = min(root.getRight());
                root.setKey((Key) temp.getKey());
                root.setValue((Value) temp.getValue());

                root.setRight(deleteMin(root.getRight()));
            } else {
                root.setRight(delete(root.getRight(), key));
            }
        }

        return balance(root);
    }

    private RBNode<Key, Value> min(RBNode<Key, Value> root) {

        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root;

    }

    private boolean contains(Key key) {
        return get(key) != null;
    }

    private RBNode<Key, Value> get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    private RBNode<Key, Value> get(RBNode<Key, Value> root, Key key) {
        RBNode<Key, Value> result = null;
        while (root != null) {
            int compareTo = key.compareTo(root.getKey());
            if (compareTo == 0) {
                result = root;
                break;
            } else if (compareTo < 0) {
                root = root.getLeft();
            } else {
                root = root.getRight();
            }
        }

        return result;
    }
}

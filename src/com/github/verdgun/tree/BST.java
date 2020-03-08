package com.github.verdgun.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉查找树
 * BinarySearchTree
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root;

    public int size() {
        if (root == null) {
            return 0;
        }

        return root.getSize();
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null!");
        }
        return get(root, key);
    }

    private Value get(Node<Key, Value> root, Key key) {
        if (root == null) {
            return null;
        }

        var compare = key.compareTo(root.getKey());
        Value res;
        if (compare == 0) {
            res = root.getValue();
        } else if (compare < 0) {
            res = get(root.getLeft(), key);
        } else {
            res = get(root.getRight(), key);
        }
        return res;
    }

    public void put(Key key, Value value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("key or value can not be null!");
        }
        root = put(root, key, value);
    }

    private Node<Key, Value> put(Node<Key, Value> root, Key key, Value value) {
        if (root == null) {
            return new Node<>(1, key, value);
        }

        var compare = key.compareTo(root.getKey());
        if (compare == 0) {
            root.setValue(value);
        } else if (compare < 0) {
            Node<Key, Value> node = put(root.getLeft(), key, value);
            root.setLeft(node);
            root.setSize(root.getSize() + 1);
            node.setParent(root);
        } else {
            Node<Key, Value> node = put(root.getRight(), key, value);
            root.setRight(node);
            root.setSize(root.getSize() + 1);
            node.setParent(root);
        }
//        root.setSize(root.getLeft().getSize() + root.getRight().getSize() + 1);
        return root;
    }

    public Key min() {
        return min(root).getKey();
    }

    private Node<Key, Value> min(Node<Key, Value> root) {
        if (root.getLeft() == null) {
            return root;
        }

        return min(root.getLeft());
    }

    /**
     * @return 小于等于Key的元素中最大的那一个
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key or value can not be null!");
        }
        Node<Key, Value> x = floor(root, key);
        if (x == null) {
            return null;
        }

        return x.getKey();
    }

    private Node<Key, Value> floor(Node<Key, Value> root, Key key) {
        if (root == null) {
            return null;
        }
        int compare = key.compareTo(root.getLeft().getKey());
        if (compare == 0) {
            return root;
        } else if (compare < 0) {
            return floor(root.getLeft(), key);
        }

        Node<Key, Value> floor = floor(root.getRight(), key);
        if (floor == null) {
            return root;
        }
        return floor;
    }

    /**
     * @param key 待查找的key
     * @return 大于等于key的元素中最小的那一个;
     */
    public Key ceiling(Key key) {
        Node<Key, Value> node = ceiling(root, key);
        if (node == null) {
            return null;
        }
        return node.getKey();
    }

    private Node<Key, Value> ceiling(Node<Key, Value> root, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key or value can not be null!");
        }
        if (root == null) {
            return null;
        }
        int compare = key.compareTo(root.getKey());
        if (compare < 0) {
            if (root.getLeft() == null) {
                return root;
            } else {
                return ceiling(root.getLeft(), key);
            }
        } else {
            return ceiling(root.getRight(), key);
        }
    }

    /**
     * 获取排名为k的元素
     * 由于排名(数组)从0开始,因此获取排名k的元素等价于获取第(k+1)个最小键
     *
     * @param k 排序序号
     * @return 第(k + 1)个最小键
     */
    public Key select(int k) {
        if (k >= root.getSize() || k < 0) {
            throw new IllegalArgumentException("argument to select() is invalid: " + "k");
        }
        Node<Key, Value> select = select(root, k);

        return select.getKey();
    }

    private Node<Key, Value> select(Node<Key, Value> root, int k) {
        if (root == null) {
            return null;
        }
        /*
         * 对于当前节点(root),由于比它小的元素都在其左子树中,因此其左子树的大小就是当前节点的排名名次(因为名次从0开始排序)
         */
        int size = size(root.getLeft());
        if (size == k) {
            return root;
        } else if (size > k) {
            return select(root.getLeft(), k);
        } else {
            return select(root.getRight(), k - size - 1);
        }

    }

    private int size(Node<Key, Value> root) {
        if (root == null) {
            return 0;
        }
        return root.getSize();
    }
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }
        return rank(root, key);
    }

    private int rank(Node<Key, Value> root, Key key) {
        if (root == null) {
            return 0;
        }

        int compare = key.compareTo(root.getKey());
        if (compare == 0) {
            return size(root.getLeft());
        } else if (compare < 0) {
            return rank(root.getLeft(), key);
        } else {
            return size(root.getLeft()) + 1 + rank(root.getRight(), key);
        }
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node<Key, Value> deleteMin(Node<Key, Value> root) {
        if (root.getLeft() == null) {
            root.getRight().setParent(root.getParent());
            return root.getRight();
        }
        root.setLeft(deleteMin(root.getLeft()));
        root.setSize(size(root.getLeft()) + size(root.getRight()) + 1);
        return root;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node<Key, Value> deleteMax(Node<Key, Value> root) {
        if (root.getRight() == null) {
            root.getLeft().setParent(root.getParent());
            return root.getLeft();
        }

        root.setRight(deleteMax(root.getRight()));
        root.setSize(size(root.getLeft()) + size(root.getRight()) + 1);
        return root;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node<Key, Value> delete(Node<Key, Value> root, Key key) {
        if (root == null) {
            return null;
        }

        int compare = key.compareTo(root.getKey());
        if (compare < 0) {
            Node<Key, Value> subRoot = delete(root.getLeft(), key);
            root.setLeft(subRoot);
            if (subRoot != null) {
                subRoot.setParent(root);
            }


        } else if (compare > 0) {
            Node<Key, Value> subRoot = delete(root.getRight(), key);
            root.setRight(subRoot);
            if (subRoot != null) {
                subRoot.setParent(root);
            }
        } else {
            root.setParent(null);
            if (root.getRight() == null) {
                return root.getLeft();
            }
            if (root.getLeft() == null) {
                return root.getRight();
            }

            Node temp = root;
            root = min(root.getRight());

            Node left = temp.getLeft();
            left.setParent(root);
            root.setLeft(left);

            root.setRight(deleteMin(temp.getRight()));

            root.getRight().setParent(root);
        }

        return root;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key min, Key max) {
        List<Key> list = new LinkedList<>();
        keys(root, list, min, max);
        return list;
    }

    private void keys(Node<Key, Value> root, List<Key> list, Key lo, Key hi) {
        if (root == null) {
            return;
        }
        int cmpLo = lo.compareTo(root.getKey());
        int cmpHi = hi.compareTo(root.getKey());

        if (cmpLo < 0) {
            keys(root.getLeft(), list, lo, hi);
        }
        if (cmpLo <= 0 && cmpHi >= 0) {
            list.add(root.getKey());
        }
        if (cmpHi > 0) {
            keys(root.getRight(), list, lo, hi);
        }
    }

    private Key max() {
        return max(root);
    }

    private Key max(Node<Key, Value> root) {
        if (root.getRight() != null) {
            return max(root.getRight());
        }

        return root.getKey();
    }


}

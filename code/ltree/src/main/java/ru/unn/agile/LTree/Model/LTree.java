package ru.unn.agile.LTree.Model;

import java.util.Collection;

public class LTree<T> {

    public static class KeyValuePair<T> {
        private final int key;
        private final T value;

        public KeyValuePair(final int key, final T value) {
            this.key = key;
            this.value = value;
        }

        public boolean compareWith(final KeyValuePair<T> pair) {
            return pair != null && key == pair.key && value.equals(pair.value);
        }

        public int getKey() {
            return key;
        }
    }

    private static class Node<T> {
        private final KeyValuePair<T> kvp;
        private int rank;
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;

        Node(final KeyValuePair<T> kvp, final int rank,
             final Node<T> left, final Node<T> right, final Node<T> parent) {
            this.kvp = kvp;
            this.rank = rank;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    public LTree() {
        root = null;
    }

    private LTree(final KeyValuePair<T> kvp) {
        add(kvp);
    }

    public LTree(final Collection<KeyValuePair<T>> array) {
        root = null;
        if (array != null) {
            for (KeyValuePair<T> kvp : array) {
                add(kvp);
            }
        }
    }

    public final void add(final KeyValuePair<T> kvp) {
        if (size() == 0) {
            root = new Node<T>(kvp, 1, null, null, null);
        } else {
            merge(new LTree<T>(kvp));
        }
    }

    private Node<T> search(final Node<T> head, final int key) {
        Node<T> tmp = null;
        if (head != null) {
            if (head.kvp.key == key) {
                tmp = head;
            } else if (key >= head.kvp.key) {
                tmp = search(head.left, key);
                if (tmp == null) {
                    tmp = search(head.right, key);
                }
            }
        }
        return tmp;
    }

    public KeyValuePair<T> remove(final int key) {
        KeyValuePair<T> retKvp = null;
        if (size() != 0) {
            Node<T> rem = search(root, key);

            if (rem == null) {
                retKvp = null;
            } else if (rem == root) {
                retKvp = root.kvp;

                root = merge(root.left, root.right);
            } else {
                retKvp = rem.kvp;

                Node<T> parentRem = rem.parent;
                Node<T> newTree = merge(rem.left, rem.right);
                if (parentRem.left == rem) {
                    parentRem.left = newTree;
                } else {
                    parentRem.right = newTree;
                }
                Node<T> tmp = parentRem;
                while (tmp != root && dist(tmp.left) < dist(tmp.right)) {
                    Node<T> swap = tmp.left;
                    tmp.left = tmp.right;
                    tmp.right = swap;
                    tmp = tmp.parent;
                }
            }
        }
        return retKvp;
    }

    public KeyValuePair<T> getMin() {
        return (size() == 0) ? null : new KeyValuePair<T>(root.kvp.key, root.kvp.value);
    }

    private int dist(final Node<T> x) {
        int ret;
        if (x == null) {
            ret = 0;
        } else {
            ret = x.rank;
        }
        return ret;
    }

    private Node<T> merge(final Node<T> fx, final Node<T> fy) {
        Node<T> x = fx;
        Node<T> y = fy;
        if (x == null) {
            return y;
        }
        if (y == null) {
            return x;
        }
        if (x.kvp.key > y.kvp.key) {
            Node<T> tmp = x;
            x = y;
            y = tmp;
        }
        x.right = merge(x.right, y);
        if (dist(x.right) > dist(x.left)) {
            Node<T> tmp = x.left;
            x.left = x.right;
            x.right = tmp;
        }

        if (x.left != null) {
            x.left.parent = x;
        }
        if (x.right != null) {
            x.right.parent = x;
        }
        x.rank = Math.min(dist(x.right), dist(x.left)) + 1;
        return x;
    }

    public boolean merge(final LTree<T> tree) {
        boolean wasMerged = true;
        if (tree == null) {
            wasMerged = false;
        } else if (size() == 0) {
            root = tree.root;
        } else if (tree.root != null) {
            root = merge(root, tree.root);
        }
        return wasMerged;
    }

    private int size(final Node root) {
        int tmp = 1;
        if (root.left != null) {
            tmp += size(root.left);
        }
        if (root.right != null) {
            tmp += size(root.right);
        }
        return tmp;
    }

    public final int size() {
        return (root == null) ? 0 : size(root);
    }

    private Node<T> root;
}
